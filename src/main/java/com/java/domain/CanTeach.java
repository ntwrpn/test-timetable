
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CanTeach")

@NamedQueries({
@NamedQuery(name = "CanTeach.getAll", query = "SELECT c from CanTeach c"),
@NamedQuery(name = "CanTeach.getById", query = "SELECT c from CanTeach c where c.id=:id")
}) 


public class CanTeach {

    
    @Id
    @Column(name = "idcan_teach")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "subject_id")
    private int subject_id;

    @Column(name = "teacher_id")
    private int teacher_id;


    public CanTeach() {
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

}

