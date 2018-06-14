package org.dayup.avatar.model.define.page;

import org.dayup.avatar.support.common.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OperateSearch implements Serializable {

    private String name;
    private OperateType type;
    private Object value;
    private LocalDate[] dates;
    private LocalDateTime[] dateTimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperateType getType() {
        return type;
    }

    public void setType(OperateType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getText() {
        return String.valueOf(value);
    }

    public LocalDate[] getDates() {
        if (type == null || !type.isDate() || value == null) {
            return null;
        }
        if (dates == null) {
            dates = new LocalDate[2];
            List<String> dateTexts = (List) value;
            if (dateTexts.get(0) != null) {
                dates[0] = DateUtil.toLocalDateTime(dateTexts.get(0)).toLocalDate();
            }
            if (dateTexts.get(1) != null) {
                dates[1] = DateUtil.toLocalDateTime(dateTexts.get(1)).toLocalDate();
            }
        }
        return dates;
    }

    public LocalDateTime[] getDateTimes() {
        if (type == null || !type.isDatetime() || value == null) {
            return null;
        }
        if (dateTimes == null) {
            dateTimes = new LocalDateTime[2];
            List<String> dateTimeTexts = (List) value;
            if (dateTimeTexts.get(0) != null) {
                dateTimes[0] = DateUtil.toLocalDateTime(dateTimeTexts.get(0));
            }
            if (dateTimeTexts.get(1) != null) {
                dateTimes[1] = DateUtil.toLocalDateTime(dateTimeTexts.get(1));
            }
        }
        return dateTimes;
    }

    public boolean isValid() {
        return this.value != null;
    }

}
