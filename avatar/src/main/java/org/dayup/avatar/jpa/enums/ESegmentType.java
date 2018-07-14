
package org.dayup.avatar.jpa.enums;

import org.dayup.avatar.jpa.base.DBEnum;

public enum ESegmentType implements DBEnum {

    MARKDOWN(1), MODEL(2), HTML(3);

    private Integer constant;

    ESegmentType(Integer constant) {
        this.constant = constant;
    }


    public static ESegmentType fromConstant(Integer constant) {
        for (ESegmentType segmentType : ESegmentType.values()) {
            if (segmentType.getConstant().equals(constant)) {
                return segmentType;
            }
        }
        return null;
    }

    @Override
    public Integer getConstant() {
        return constant;
    }
}
