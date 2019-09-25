
package com.javamaster.domain;

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
    @Column(name = "idsubgroup")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "count_of_student")
    private int count_of_student;

    @Column(name = "group_id")
    private int group_id;


    public Subgroup() {
    }

    
    public int getCount_of_student() {
        return count_of_student;
    }
    
    public void setCount_of_student(int count_of_student) {
        this.count_of_student=count_of_student;
    }

    public int getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(int group_id) {
        this.group_id=group_id;
    }

}

