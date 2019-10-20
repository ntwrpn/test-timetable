
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Corps")

@NamedQueries({
@NamedQuery(name = "Corps.getAll", query = "SELECT c from Corps c"),
@NamedQuery(name = "Corps.getById", query = "SELECT c from Corps c where c.id=:id")
}) 


public class Corps {

    
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

    @Column(name = "descr")
    private String descr;

    @Column(name = "street")
    private String street;


    public Corps() {
    }

    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street=street;
    }

}

