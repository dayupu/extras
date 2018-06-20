package org.dayup.avatar.jpa.entity;

import org.dayup.avatar.jpa.base.DataSequence;
import org.dayup.avatar.jpa.base.EntityDate;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doc_document")
public class Document extends EntityDate implements DataSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long libId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libId", insertable = false, updatable = false)
    private Library library;

    @Column
    private Integer sequence;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "Text")
    private String content;

    @Column
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.EDataStatus") })
    private EDataStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EDataStatus getStatus() {
        return status;
    }

    public void setStatus(EDataStatus status) {
        this.status = status;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
