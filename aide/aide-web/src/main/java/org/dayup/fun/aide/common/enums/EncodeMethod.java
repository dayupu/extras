package org.dayup.fun.aide.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EncodeMethod implements MvcEnum {

    // encode
    BASE64("base64"), ASCII("ascii"), UNICODE("unicode"), URL("url");

    private String value;

    EncodeMethod(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String value() {
        return this.value;
    }
}
