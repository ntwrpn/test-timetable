
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@NamedQueries({
@NamedQuery(name = "Groups.getAll", query = "SELECT c from Groups c"),
@NamedQuery(name = "Groups.getById", query = "SELECT c from Groups c where c.id=:id")
}) 
public class Groups {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="flow", referencedColumnName="id", nullable = true)
    @JsonBackReference(value="flow-group")
    private Flow flow=null;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  


    @OneToMany(mappedBy="groups", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference //(value="subgroup-group")
    @Column(nullable = true)
    private Set<Subgroup> subgroup;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Groups() {
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }


    public Set<Subgroup> getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Set<Subgroup> subgroup) {
        this.subgroup = subgroup;
    }

}

