
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lecterntype")
@NamedQueries({
@NamedQuery(name = "LecternType.getAll", query = "SELECT c from LecternType c"),
@NamedQuery(name = "LecternType.getById", query = "SELECT c from LecternType c where c.id=:id")
}) 
public class LecternType {


    @Id
    @Column(name = "idlectern_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "descr")
    private String descr;
    
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LecternType() {
    }

    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
    }

}

