
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")

@NamedQueries({
@NamedQuery(name = "Course.getAll", query = "SELECT c from Course c"),
@NamedQuery(name = "Course.getById", query = "SELECT c from Course c where c.id=:id")
}) 


public class Course {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    


    @Column(name = "total")
    private int total;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<Week> week;
       
    @OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<OccupationCounter> countOccupation;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="schedule", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Week> getWeek() {
        return week;
    }

    public void setWeek(Set<Week> week) {
        this.week = week;
    }

    public Set<OccupationCounter> getCountOccupation() {
        return countOccupation;
    }

    public void setCountOccupation(Set<OccupationCounter> countOccupation) {
        this.countOccupation = countOccupation;
    }


}

