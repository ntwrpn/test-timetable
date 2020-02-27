
package com.java.domain;

import javax.persistence.*;

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

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;


    public Corps() {
    }

    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street=street;
    }

}

