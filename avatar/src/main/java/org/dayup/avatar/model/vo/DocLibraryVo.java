package org.dayup.avatar.model.vo;


import java.io.Serializable;
import java.time.LocalDateTime;

public class DocLibraryVo implements Serializable {

    private static final long serialVersionUID = 646259566741158909L;
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
