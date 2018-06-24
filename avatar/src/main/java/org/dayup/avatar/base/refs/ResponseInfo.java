
package org.dayup.avatar.base.refs;

import java.io.Serializable;

public class ResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = -3530664103261213545L;

    private String code = "200";
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
