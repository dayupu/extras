
package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.base.exceptions.BusinessException;
import org.dayup.avatar.base.refs.EMessage;
import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.jpa.entity.DataClass;
import org.dayup.avatar.jpa.entity.DataColumn;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.enums.ERequired;
import org.dayup.avatar.jpa.repository.DataClassRepo;
import org.dayup.avatar.jpa.repository.DataColumnRepo;
import org.dayup.avatar.web.model.parser.DataClassParser;
import org.dayup.avatar.web.model.vo.DataClassVo;
import org.dayup.avatar.web.model.vo.DataColumnVo;
import org.dayup.avatar.web.model.vo.DataTypeVo;
import org.dayup.avatar.service.docs.IDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataModelServiceImpl implements IDataModelService {

    @Autowired
    private DataClassRepo dataClassRepo;

    @Autowired
    private DataColumnRepo dataColumnRepo;

    @Transactional
    @Override
    public List<DataClassVo> getDataClassListWithLibId(Long libId) {

        List<DataClass> dataClassList = dataClassRepo.findByLibIdAndStatusOrderBySequence(libId, EDataStatus.INIT);
        List<DataClassVo> classVoList = new ArrayList<>();
        for (DataClass dataClass : dataClassList) {
            classVoList.add(DataClassParser.toVo(dataClass));
        }
        return classVoList;
    }

    @Transactional
    @Override
    public List<DataTypeVo> getDataTypesWithLibId(Long libId) {
        List<DataClass> dataClassList = dataClassRepo.findTypesByLibId(libId, EDataStatus.INIT);
        List<DataTypeVo> dbDataTypes = new ArrayList<>();
        DataTypeVo dataTypeVo;
        for (DataClass dataClass : dataClassList) {
            dataTypeVo = new DataTypeVo();
            dataTypeVo.setValue(dataClass.getId());
            dataTypeVo.setLabel(dataClass.getName());
            dbDataTypes.add(dataTypeVo);
        }

        List<DataTypeVo> dataTypes = new ArrayList<>();
        DataTypeVo listType = new DataTypeVo();
        listType.setValue(0L);
        listType.setLabel("List");
        listType.setChildren(dbDataTypes);
        dataTypes.add(listType);
        dataTypes.addAll(dbDataTypes);
        return dataTypes;
    }

    @Transactional
    @Override
    public DataClassVo getClass(Long classId) {
        DataClass dataClass = dataClassRepo.findByIdAndStatus(classId, EDataStatus.INIT);
        if (dataClass == null) {
            throw new BusinessException(EMessage.DOC01004);
        }
        return DataClassParser.toVo(dataClass);
    }

    @Transactional
    @Override
    public void drop(Long classId) {
        DataClass dataClass = dataClassRepo.findByIdAndStatus(classId, EDataStatus.INIT);
        if (dataClass == null) {
            throw new BusinessException(EMessage.DOC01004);
        }
        dataClass.setStatus(EDataStatus.DELETED);
    }

    @Transactional
    @Override
    public DataClassVo saveClass(DataClassVo classVo) {
        Long libId = IDSecure.decode(classVo.getLibId());
        DataClass duplicate = dataClassRepo.findTop1ByLibIdAndNameAndStatus(libId, classVo.getName(), EDataStatus.INIT);
        DataClass dataClass = null;
        if (classVo.getId() == null) {
            if (duplicate != null) {
                throw new BusinessException(EMessage.DOC01005);
            }
            DataClass lastClass = dataClassRepo.findTop1ByLibIdOrderBySequenceDesc(libId);
            Integer sequence = 20;
            if (lastClass != null && lastClass.getSequence() != null) {
                sequence = lastClass.getSequence() + 1;
            }
            dataClass = new DataClass();
            dataClass.setLibId(libId);
            dataClass.setLabel(classVo.getLabel());
            dataClass.setName(classVo.getName());
            dataClass.setStatus(EDataStatus.INIT);
            dataClass.setSequence(sequence);
            if (classVo.getColumns() != null) {
                dataClass.updateColumns(extractDataColumns(classVo));
            }
            dataClass = dataClassRepo.save(dataClass);
        } else {
            dataClass = dataClassRepo.getOne(IDSecure.decode(classVo.getId()));
            if (dataClass == null) {
                throw new BusinessException(EMessage.DOC01004);
            }
            if (duplicate != null && !duplicate.getId().equals(dataClass.getId())) {
                throw new BusinessException(EMessage.DOC01005);
            }
            dataClass.setLabel(classVo.getLabel());
            dataClass.setName(classVo.getName());
            if (classVo.getColumns() != null) {
                dataClass.updateColumns(extractDataColumns(classVo));
            }
        }
        return DataClassParser.toVo(dataClass);
    }

    private List<DataColumn> extractDataColumns(DataClassVo classVo) {
        DataColumn column;
        List<DataColumn> columns = new ArrayList<>();
        for (DataColumnVo columnVo : classVo.getColumns()) {
            column = new DataColumn();
            column.setName(columnVo.getName());
            List<Long> types = columnVo.getTypes();
            if (!CollectionUtils.isEmpty(types)) {
                if (types.size() > 1) {
                    column.setList(true);
                    column.setTypeId(types.get(1));
                } else {
                    column.setList(false);
                    column.setTypeId(types.get(0));
                }
            }
            column.setSample(columnVo.getSample());
            column.setRequired(ERequired.fromConstant(columnVo.getRequired()));
            column.setDescription(columnVo.getDescription());
            column.setDefaultValue(columnVo.getDefaultValue());
            columns.add(column);
        }
        return columns;
    }
}
