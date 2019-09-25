
package com.javamaster.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Lesson")

@NamedQueries({
@NamedQuery(name = "Lesson.getAll", query = "SELECT c from Lesson c"),
@NamedQuery(name = "Lesson.getById", query = "SELECT c from Lesson c where c.id=:id")
}) 


public class Lesson {

    
    @Id
    @Column(name = "id_lesson")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "classroom_id")
    private int classroom_id;

    @Column(name = "day_id")
    private int day_id;

    @Column(name = "needed_class_id")
    private int needed_class_id;

    @Column(name = "subject_id")
    private int subject_id;

    @Column(name = "teacher_id")
    private int teacher_id;

    @Column(name = "turn_id")
    private int turn_id;


    public Lesson() {
    }

    
    public int getClassroom_id() {
        return classroom_id;
    }
    
    public void setClassroom_id(int classroom_id) {
        this.classroom_id=classroom_id;
    }

    public int getDay_id() {
        return day_id;
    }
    
    public void setDay_id(int day_id) {
        this.day_id=day_id;
    }

    public int getNeeded_class_id() {
        return needed_class_id;
    }
    
    public void setNeeded_class_id(int needed_class_id) {
        this.needed_class_id=needed_class_id;
    }

    public int getSubject_id() {
        return subject_id;
    }
    
    public void setSubject_id(int subject_id) {
        this.subject_id=subject_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }
    
    public void setTeacher_id(int teacher_id) {
        this.teacher_id=teacher_id;
    }

    public int getTurn_id() {
        return turn_id;
    }
    
    public void setTurn_id(int turn_id) {
        this.turn_id=turn_id;
    }

}

