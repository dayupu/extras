
package org.dayup.avatar.web.model.vo;

import java.util.List;

public class DataTypeVo {

    private String label;
    private Long value;
    private List<DataTypeVo> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public List<DataTypeVo> getChildren() {
        return children;
    }

    public void setChildren(List<DataTypeVo> children) {
        this.children = children;
    }

}
