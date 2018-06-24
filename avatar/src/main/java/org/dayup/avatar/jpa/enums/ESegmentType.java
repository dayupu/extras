
package org.dayup.avatar.jpa.enums;

import org.dayup.avatar.jpa.base.DBEnum;

public enum ESegmentType implements DBEnum {

    MARKDOWN(1), MODEL(2), HTML(3);

    private Integer constant;

    ESegmentType(Integer constant) {
        this.constant = constant;
    }

    @Override
    public Integer getConstant() {
        return constant;
    }
}
