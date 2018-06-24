
package org.dayup.avatar.jpa.enums;

import org.dayup.avatar.jpa.base.DBEnum;

public enum ERequired implements DBEnum {

    NO(0), YES(1);

    private Integer constant;

    ERequired(Integer constant) {
        this.constant = constant;
    }

    @Override
    public Integer getConstant() {
        return constant;
    }

    public static ERequired fromConstant(Integer constant) {
        if (constant != null) {
            for (ERequired required : ERequired.values()) {
                if (required.constant.equals(constant)) {
                    return required;
                }
            }
        }
        return ERequired.NO;
    }
}
