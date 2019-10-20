
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LearningSeverity")

@NamedQueries({
@NamedQuery(name = "LearningSeverity.getAll", query = "SELECT c from LearningSeverity c"),
@NamedQuery(name = "LearningSeverity.getById", query = "SELECT c from LearningSeverity c where c.id=:id")
}) 


public class LearningSeverity {

    
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


    public LearningSeverity() {
    }

    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

