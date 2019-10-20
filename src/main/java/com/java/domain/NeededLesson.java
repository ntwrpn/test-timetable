
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NeededLesson")

@NamedQueries({
@NamedQuery(name = "NeededLesson.getAll", query = "SELECT c from NeededLesson c"),
@NamedQuery(name = "NeededLesson.getById", query = "SELECT c from NeededLesson c where c.id=:id")
}) 


public class NeededLesson {

    
    @Id
    @Column(name = "idneeded_lesson")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "speciality_id")
    private int speciality_id;

    @Column(name = "time")
    private int time;

    @Column(name = "type_of_lesson_id")
    private int type_of_lesson_id;


    public NeededLesson() {
    }

    
    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname=fullname;
    }

    public int getSpeciality_id() {
        return speciality_id;
    }
    
    public void setSpeciality_id(int speciality_id) {
        this.speciality_id=speciality_id;
    }

    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time=time;
    }

    public int getType_of_lesson_id() {
        return type_of_lesson_id;
    }
    
    public void setType_of_lesson_id(int type_of_lesson_id) {
        this.type_of_lesson_id=type_of_lesson_id;
    }

}

