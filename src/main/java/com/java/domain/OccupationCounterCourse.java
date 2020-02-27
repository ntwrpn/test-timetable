
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "occupationcountercourse")

@NamedQueries({
@NamedQuery(name = "OccupationCounterCourse.getAll", query = "SELECT c from OccupationCounterCourse c"),
@NamedQuery(name = "OccupationCounterCourse.getById", query = "SELECT c from OccupationCounterCourse c where c.id=:id")
}) 


public class OccupationCounterCourse {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "count")
    private int count;
   
    /*@ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="schedule", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Schedule schedule;*/
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id")
    @JsonBackReference(value="occupation-movement")
	private Occupation occupation;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="course", referencedColumnName="id")
    @JsonBackReference(value="course-movement")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /*public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }*/

}

