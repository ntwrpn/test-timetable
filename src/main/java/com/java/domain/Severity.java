
package com.java.domain;

import javax.persistence.*;


@Entity
@Table(name = "severity")
@NamedQueries({
@NamedQuery(name = "Severity.getAll", query = "SELECT c from Severity c"),
@NamedQuery(name = "Severity.getById", query = "SELECT c from Severity c where c.id=:id")
}) 
public class Severity {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

