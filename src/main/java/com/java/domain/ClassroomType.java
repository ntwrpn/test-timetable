
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ClassroomType")

@NamedQueries({
@NamedQuery(name = "ClassroomType.getAll", query = "SELECT c from ClassroomType c"),
@NamedQuery(name = "ClassroomType.getById", query = "SELECT c from ClassroomType c where c.id=:id")
}) 


public class ClassroomType {

    
    @Id
    @Column(name = "idclassroom_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "classroom_typec_desc")
    private String classroom_typec_desc;


    public ClassroomType() {
    }

    
    public String getClassroom_typec_desc() {
        return classroom_typec_desc;
    }
    
    public void setClassroom_typec_desc(String classroom_typec_desc) {
        this.classroom_typec_desc=classroom_typec_desc;
    }

}

