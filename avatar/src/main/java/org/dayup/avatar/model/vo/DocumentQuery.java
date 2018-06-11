package org.dayup.avatar.model.vo;

import org.dayup.avatar.model.define.PageQuery;

import java.io.Serializable;

public class DocumentQuery extends PageQuery implements Serializable {

    private static final long serialVersionUID = -8396851534222430124L;

    private String libId;

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }
}

