
package org.dayup.avatar.web.model.parser;

import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.jpa.entity.DataClass;
import org.dayup.avatar.jpa.entity.DataColumn;
import org.dayup.avatar.web.model.vo.DataClassVo;
import org.dayup.avatar.web.model.vo.DataColumnVo;

import java.util.ArrayList;

public class DataClassParser {

    public static DataClassVo toVo(DataClass dataClass) {
        DataClassVo classVo = new DataClassVo();
        classVo.setId(IDSecure.encode(dataClass.getId()));
        classVo.setLibId(IDSecure.encode(dataClass.getLibId()));
        classVo.setName(dataClass.getName());
        classVo.setTopic(dataClass.getName());
        classVo.setLabel(dataClass.getLabel());
        classVo.setColumns(new ArrayList<>());
        DataColumnVo columnVo;
        if (dataClass.getColumns() != null) {
            for (DataColumn dataColumn : dataClass.getColumns()) {
                columnVo = new DataColumnVo();
                columnVo.setName(dataColumn.getName());
                columnVo.setRequired(dataColumn.getRequired().getConstant());
                if (dataColumn.isList()) {
                    columnVo.getTypes().add(0L);
                }
                columnVo.getTypes().add(dataColumn.getTypeId());
                columnVo.setDefaultValue(dataColumn.getDefaultValue());
                columnVo.setDescription(dataColumn.getDescription());
                columnVo.setSample(dataColumn.getSample());
                classVo.getColumns().add(columnVo);
            }
        }
        return classVo;
    }
}
