
package com.java.domain;

import javax.persistence.*;

@Entity
@Table(name = "typeoflesson")

@NamedQueries({
@NamedQuery(name = "TypeOfLesson.getAll", query = "SELECT c from TypeOfLesson c"),
@NamedQuery(name = "TypeOfLesson.getById", query = "SELECT c from TypeOfLesson c where c.id=:id")
}) 


public class TypeOfLesson {

    
    @Id
    @Column(name = "idtype_of_class")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "type_of_lesson")
    private String type_of_lesson;


    public TypeOfLesson() {
    }

    
    public String getType_of_lesson() {
        return type_of_lesson;
    }
    
    public void setType_of_lesson(String type_of_lesson) {
        this.type_of_lesson=type_of_lesson;
    }

}

