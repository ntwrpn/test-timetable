
package com.java.domain;

import javax.persistence.*;


@Entity
@Table(name = "speciality")

@NamedQueries({
@NamedQuery(name = "Speciality.getAll", query = "SELECT c from Speciality c"),
@NamedQuery(name = "Speciality.getById", query = "SELECT c from Speciality c where c.id=:id")
}) 


public class Speciality {

    
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
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="lectern", referencedColumnName="id", nullable = true)
    private Lectern lectern;

    @Column(name = "name")
    private String name;


    public Speciality() {
    }

    public Lectern getLectern() {
        return lectern;
    }

    public void setLectern(Lectern lectern) {
        this.lectern = lectern;
    }
    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

