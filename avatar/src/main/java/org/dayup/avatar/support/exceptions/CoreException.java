package org.dayup.avatar.support.exceptions;

import org.dayup.avatar.support.constants.EMessage;

public abstract class CoreException extends RuntimeException {

    public EMessage msg;

    public CoreException(EMessage msg) {
        super(wrapMsg(msg));
        this.msg = msg;
    }

    public CoreException(EMessage msg, String message) {
        super(wrapMsg(msg) + "-" + message);
        this.msg = msg;
    }

    private static String wrapMsg(EMessage msg) {
        return "[" + msg.getCode() + ":" + msg.getMsgKey() + "]";
    }

    public EMessage getMsg() {
        return msg;
    }
}
