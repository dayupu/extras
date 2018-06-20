package org.dayup.avatar.jpa.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "doc_data_class")
public class DataClass extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer sequence;

    @Column(nullable = false)
    private String name;

    @Column
    private String label;

    @Column(columnDefinition = "Boolean")
    private Boolean isOrigin = Boolean.FALSE;

    @Column
    private Long libId;

    @Column
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.EDataStatus")})
    private EDataStatus status = EDataStatus.INIT;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "dataClass")
    @OrderBy("sequence asc")
    private List<DataColumn> columns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EDataStatus getStatus() {
        return status;
    }

    public void setStatus(EDataStatus status) {
        this.status = status;
    }

    public List<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
    }
}
