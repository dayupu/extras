package org.dayup.avatar.jpa.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import java.util.List;

@Entity
@Table(name = "admin_menu")
public class AdminMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    private Long pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private AdminMenu parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<AdminMenu> childrens;

    @Column
    private Integer level = 0;

    @Column
    private String url;

    @Column
    private Boolean active = true;

    @Column(unique = true, nullable = false)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public AdminMenu getParent() {
        return parent;
    }

    public void setParent(AdminMenu parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdminMenu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<AdminMenu> childrens) {
        this.childrens = childrens;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
