package org.dayup.avatar.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataColumnVo implements Serializable {

    private static final long serialVersionUID = 2885188966113193000L;
    private String name;
    private List<Long> types = new ArrayList<>();
    private Integer required;
    private String sample;
    private String description;
    private String defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getTypes() {
        return types;
    }

    public void setTypes(List<Long> types) {
        this.types = types;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
