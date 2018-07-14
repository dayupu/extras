
package org.dayup.avatar.jpa.entity;

import org.dayup.avatar.jpa.base.DataSequence;
import org.dayup.avatar.jpa.base.EntityDate;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.enums.ESegmentType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "doc_segment")
public class Segment extends EntityDate implements DataSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, insertable = false, updatable = false)
    private Long docId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docId")
    private Document document;

    @Column(insertable = false, updatable = false)
    private Long pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Segment parent;

    @Column
    private Integer sequence;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.ESegmentType")})
    private ESegmentType type;

    @Column(columnDefinition = "Text")
    private String raw;

    @Column(columnDefinition = "Text")
    private String html;

    @Column
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.EDataStatus")})
    private EDataStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "parent")
    @OrderBy("sequence asc")
    private List<Segment> childrens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Segment getParent() {
        return parent;
    }

    public void setParent(Segment parent) {
        this.parent = parent;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ESegmentType getType() {
        return type;
    }

    public void setType(ESegmentType type) {
        this.type = type;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public EDataStatus getStatus() {
        return status;
    }

    public void setStatus(EDataStatus status) {
        this.status = status;
    }

    public List<Segment> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Segment> childrens) {
        this.childrens = childrens;
    }
}
