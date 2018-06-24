
package org.dayup.avatar.web.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DocumentVo implements Serializable {

    private static final long serialVersionUID = -6673506571752429679L;

    private String id;
    private String title;
    private String description;
    private Integer sequence;
    private Integer sequenceNew;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getSequenceNew() {
        return sequenceNew;
    }

    public void setSequenceNew(Integer sequenceNew) {
        this.sequenceNew = sequenceNew;
    }
}
