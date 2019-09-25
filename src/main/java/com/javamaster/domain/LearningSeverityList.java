
package com.javamaster.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LearningSeverityList")

@NamedQueries({
@NamedQuery(name = "LearningSeverityList.getAll", query = "SELECT c from LearningSeverityList c"),
@NamedQuery(name = "LearningSeverityList.getById", query = "SELECT c from LearningSeverityList c where c.id=:id"),
@NamedQuery(name = "LearningSeverityList.getLastId", query = "SELECT c from LearningSeverityList c order by id desc")
}) 


public class LearningSeverityList {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name_id")
    private int name_id;

    @Column(name = "hours")
    private int hours;


    public LearningSeverityList() {
    }

    
    public int getName_id() {
        return name_id;
    }
    
    public void setName_id(int name_id) {
        this.name_id=name_id;
    }

    public int getHours() {
        return hours;
    }
    
    public void setHours(int hours) {
        this.hours=hours;
    }

}

