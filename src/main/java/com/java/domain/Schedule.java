
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule")

@NamedQueries({
@NamedQuery(name = "Schedule.getAll", query = "SELECT c from Schedule c"),
@NamedQuery(name = "Schedule.getById", query = "SELECT c from Schedule c where c.id=:id")
}) 


public class Schedule {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<Course> courses;
       
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<OccupationCounter> countOccupation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<OccupationCounter> getCountOccupation() {
        return countOccupation;
    }

    public void setCountOccupation(Set<OccupationCounter> countOccupation) {
        this.countOccupation = countOccupation;
    }

    


}

