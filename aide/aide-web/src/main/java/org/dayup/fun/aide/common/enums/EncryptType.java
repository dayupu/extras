package org.dayup.fun.aide.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EncryptType implements MvcEnum {

    // encode
    BASE64("base64"), ASCII("ascii"), UNICODE("unicode"), URL("url"),
    // hash
    SHA1("sha1"), SHA256("sha256"), SHA512("sha512"), MD5("md5"),
    // encrypt
    AES("aes");

    private String value;

    EncryptType(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String value() {
        return this.value;
    }
}
