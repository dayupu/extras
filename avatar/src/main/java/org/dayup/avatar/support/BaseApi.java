package org.dayup.avatar.support;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.CoreException;

public abstract class BaseApi {

    protected <T> ResponseInfo<T> wrapError() {
        return wrapResponse(EMessage.SYSTEM_ERROR);
    }

    protected <T> ResponseInfo<T> wrapException(CoreException e) {
        return wrapResponse(e.getMsg());
    }

    protected <T> ResponseInfo<T> wrapSuccess(T data) {
        return wrapResponse(EMessage.SUCCESS, data);
    }

    protected <T> ResponseInfo<T> wrapResponse(EMessage message) {
        return wrapResponse(message, null);
    }

    protected <T> ResponseInfo<T> wrapResponse(EMessage message, T data) {
        return wrapResponse(message.getCode(), msg(message.getMsgKey()), data);
    }

    protected <T> ResponseInfo<T> wrapResponse(String code, String message, T data) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(code);
        responseInfo.setMsg(message);
        responseInfo.setData(data);
        return responseInfo;
    }

    protected String msg(String key, String... args) {
        return MessageSupplier.getMsg(key, args);
    }
}
