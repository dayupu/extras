package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.jpa.entity.DataClass;
import org.dayup.avatar.jpa.entity.DataColumn;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.repository.DataClassRepo;
import org.dayup.avatar.model.vo.DataClassVo;
import org.dayup.avatar.model.vo.DataColumnVo;
import org.dayup.avatar.model.vo.DataTypeVo;
import org.dayup.avatar.service.docs.IDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataModelServiceImpl implements IDataModelService {

    @Autowired
    private DataClassRepo dataClassRepo;

    @Transactional
    @Override
    public List<DataClassVo> getDataClassListWithLibId(Long libId) {

        List<DataClass> dataClassList = dataClassRepo.findByLibIdAndStatusOrderBySequence(libId, EDataStatus.INIT);
        DataClassVo classVo;
        DataColumnVo columnVo;
        List<DataClassVo> classVoList = new ArrayList<>();
        for (DataClass dataClass : dataClassList) {
            classVo = new DataClassVo();
            classVo.setName(dataClass.getName());
            classVo.setTopic(dataClass.getName());
            classVo.setLabel(dataClass.getLabel());
            classVo.setColumns(new ArrayList<>());
            for (DataColumn dataColumn : dataClass.getColumns()) {
                columnVo = new DataColumnVo();
                columnVo.setName(dataColumn.getName());
                columnVo.setRequired(dataColumn.getRequired().getConstant());
                if (dataColumn.isList()) {
                    columnVo.getTypes().add(0L);
                }
                columnVo.getTypes().add(dataColumn.getClassId());
                columnVo.setDefaultValue(dataColumn.getDefaultValue());
                columnVo.setDescription(dataColumn.getDescription());
                columnVo.setSample(dataColumn.getSample());
                classVo.getColumns().add(columnVo);
            }
            classVoList.add(classVo);
        }
        return classVoList;
    }

    @Transactional
    @Override
    public List<DataTypeVo> getDataTypesWithLibId(Long libId) {
        List<DataClass> dataClassList = dataClassRepo.findTypesByLibId(libId, EDataStatus.INIT);
        List<DataTypeVo> dataTypeVos = new ArrayList<>();
        DataTypeVo dataTypeVo;
        for (DataClass dataClass : dataClassList) {
            dataTypeVo = new DataTypeVo();
            dataTypeVo.setValue(dataClass.getId());
            dataTypeVo.setLabel(dataClass.getName());
            dataTypeVos.add(dataTypeVo);
        }
        return dataTypeVos;
    }
}
