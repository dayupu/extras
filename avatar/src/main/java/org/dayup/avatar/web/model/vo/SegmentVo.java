package org.dayup.avatar.web.model.vo;

import java.io.Serializable;

public class SegmentVo implements Serializable {

    private static final long serialVersionUID = -8473642723034081952L;

    private String id;

    private String pid;

    private String title;

    private String docId;

    private Integer type;


    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
