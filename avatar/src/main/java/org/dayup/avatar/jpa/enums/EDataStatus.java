package org.dayup.avatar.jpa.enums;

import org.dayup.avatar.jpa.base.DBEnum;

public enum EDataStatus implements DBEnum {

    INIT(0), DELETED(3);

    private Integer constant;

    EDataStatus(Integer constant) {
        this.constant = constant;
    }

    @Override
    public Integer getConstant() {
        return constant;
    }
}
