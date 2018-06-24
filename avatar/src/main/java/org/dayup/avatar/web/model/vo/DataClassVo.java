
package org.dayup.avatar.web.model.vo;

import java.io.Serializable;
import java.util.List;

public class DataClassVo  implements Serializable {

    private static final long serialVersionUID = 2108501654909611700L;
    private String id;
    private String libId;
    private String name;
    private String topic;
    private String label;
    private boolean editFlag = false;
    private List<DataColumnVo> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEditFlag() {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    public List<DataColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumnVo> columns) {
        this.columns = columns;
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
