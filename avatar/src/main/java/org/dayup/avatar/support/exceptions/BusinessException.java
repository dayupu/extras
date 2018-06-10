package org.dayup.avatar.support.exceptions;

import org.dayup.avatar.support.constants.EMessage;

public class BusinessException extends CoreException {


    public BusinessException(EMessage msg) {
        super(msg);
    }

    public BusinessException(EMessage msg, String message) {
        super(msg, message);
    }
}
