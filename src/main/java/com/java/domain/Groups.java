
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Groups")
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
    @JsonBackReference
    private Flow flow=null;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  
	
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="faculty", referencedColumnName="id", nullable = true)
    private Faculty faculty;  

    public Faculty getFaculty() {
        return faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


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

}

