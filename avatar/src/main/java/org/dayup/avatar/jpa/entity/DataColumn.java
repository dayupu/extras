package org.dayup.avatar.jpa.entity;

import org.dayup.avatar.jpa.enums.ERequired;
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
import javax.persistence.Table;

@Entity
@Table(name = "doc_data_column")
public class DataColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Boolean isList = Boolean.FALSE;

    @Column(insertable = false, updatable = false)
    private Long classId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.ALL })
    @JoinColumn(name = "classId")
    private DataClass dataClass;

    @Column
    private Integer sequence;

    @Column(columnDefinition = "Text")
    private String sample;

    @Column(columnDefinition = "Text")
    private String description;

    @Column
    private String defaultValue;

    @Column
    @Type(type = "org.dayup.avatar.jpa.base.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "org.dayup.avatar.jpa.enums.ERequired") })
    private ERequired required = ERequired.NO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isList() {
        return isList;
    }

    public void setList(Boolean list) {
        isList = list;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public DataClass getDataClass() {
        return dataClass;
    }

    public void setDataClass(DataClass dataClass) {
        this.dataClass = dataClass;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ERequired getRequired() {
        return required;
    }

    public void setRequired(ERequired required) {
        this.required = required;
    }


}
