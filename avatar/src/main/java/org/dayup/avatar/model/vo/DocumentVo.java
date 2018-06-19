package org.dayup.avatar.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DocumentVo implements Serializable {

    private static final long serialVersionUID = -6673506571752429679L;

    private String id;
    private String libId;
    private String title;
    private Integer sequence;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Integer newSequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getNewSequence() {
        return newSequence;
    }

    public void setNewSequence(Integer newSequence) {
        this.newSequence = newSequence;
    }
}
