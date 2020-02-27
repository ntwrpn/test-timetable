
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;


@Entity
@Table(name = "Semester")

@NamedQueries({
@NamedQuery(name = "Semester.getAll", query = "SELECT c from Semester c"),
@NamedQuery(name = "Semester.getById", query = "SELECT c from Semester c where c.id=:id")
}) 


public class Semester {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "number")
    private int number;

    @Column(name = "hoursPerWeek")
    private int hoursPerWeek;
    
    @Column(name = "creditUnits")
    private int creditUnits;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getCreditUnits() {
        return creditUnits;
    }

    public void setCreditUnits(int creditUnits) {
        this.creditUnits = creditUnits;
    }
    

}

