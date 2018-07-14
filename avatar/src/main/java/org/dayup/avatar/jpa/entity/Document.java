
package org.dayup.avatar.jpa.entity;

import org.dayup.avatar.jpa.base.DataSequence;
import org.dayup.avatar.jpa.base.EntityDate;
import org.dayup.avatar.jpa.enums.EDataStatus;
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
@Table(name = "doc_document")
public class Document extends EntityDate implements DataSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Integer sequence;

    @Column
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.EDataStatus")})
    private EDataStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "document")
    @OrderBy("sequence asc")
    private List<Segment> segments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EDataStatus getStatus() {
        return status;
    }

    public void setStatus(EDataStatus status) {
        this.status = status;
    }

    @Override
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
