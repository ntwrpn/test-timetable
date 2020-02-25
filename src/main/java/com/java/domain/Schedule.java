
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@JsonManagedReference(value="courses-movement")
    private List<Course> courses;
       
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="countOccupation-movement")
    private List<OccupationCounter> countOccupation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<OccupationCounter> getCountOccupation() {
        return countOccupation;
    }

    public void setCountOccupation(List<OccupationCounter> countOccupation) {
        this.countOccupation = countOccupation;
    }

    


}

