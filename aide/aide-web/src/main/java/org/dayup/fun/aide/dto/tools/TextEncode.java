package org.dayup.fun.aide.dto.tools;

import org.dayup.fun.aide.common.enums.EncodeMethod;

import java.io.Serializable;

public class TextEncode implements Serializable {

    private static final long serialVersionUID = 2751091301513367310L;

    private String inputText;
    private String outputText;
    private Integer opFlag;
    private EncodeMethod method;
    private boolean success = true;
    private String message;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public EncodeMethod getMethod() {
        return method;
    }

    public void setMethod(EncodeMethod method) {
        this.method = method;
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

    public Integer getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Integer opFlag) {
        this.opFlag = opFlag;
    }
}
