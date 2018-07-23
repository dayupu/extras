package org.dayup.fun.aide.dto.tools;

import org.dayup.fun.aide.common.enums.EncryptType;

import java.io.Serializable;

public class TextEncryption implements Serializable {

    private static final long serialVersionUID = -8721449704634450192L;

    private String plaintext;
    private String ciphertext;
    private String secretKey;
    private EncryptType encryptType;
    private boolean success = true;
    private String message;

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public EncryptType getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(EncryptType encryptType) {
        this.encryptType = encryptType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
