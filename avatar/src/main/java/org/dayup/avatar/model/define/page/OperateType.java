package org.dayup.avatar.model.define.page;

public enum OperateType {
    text, date, datetime;

    public boolean isText() {
        return this == text;
    }

    public boolean isDate() {
        return this == date;
    }

    public boolean isDatetime() {
        return this == datetime;
    }
}
