
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Subgroup")

@NamedQueries({
@NamedQuery(name = "Subgroup.getAll", query = "SELECT c from Subgroup c"),
@NamedQuery(name = "Subgroup.getById", query = "SELECT c from Subgroup c where c.id=:id")
}) 


public class Subgroup {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;

    @Column(name = "count_of_student")
    private int count_of_student;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="groups", referencedColumnName="id", nullable = true)
    private Groups groups;

    public Subgroup() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    
    public int getCount_of_student() {
        return count_of_student;
    }
    
    public void setCount_of_student(int count_of_student) {
        this.count_of_student=count_of_student;
    }



}

