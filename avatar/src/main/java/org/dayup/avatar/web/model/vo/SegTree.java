package org.dayup.avatar.web.model.vo;

import java.util.List;

public class SegTree {

    private String id;
    private String label;
    private List<SegTree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SegTree> getChildren() {
        return children;
    }

    public void setChildren(List<SegTree> children) {
        this.children = children;
    }
}
