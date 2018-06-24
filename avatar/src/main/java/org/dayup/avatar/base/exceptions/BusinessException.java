
package org.dayup.avatar.base.exceptions;

import org.dayup.avatar.base.refs.EMessage;

public class BusinessException extends CoreException {


    public BusinessException(EMessage msg) {
        super(msg);
    }

    public BusinessException(EMessage msg, String message) {
        super(msg, message);
    }
}
