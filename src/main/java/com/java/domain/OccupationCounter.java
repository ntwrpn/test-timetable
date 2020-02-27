
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;


@Entity
@Table(name = "occupationcounter")

@NamedQueries({
@NamedQuery(name = "OccupationCounter.getAll", query = "SELECT c from OccupationCounter c"),
@NamedQuery(name = "OccupationCounter.getById", query = "SELECT c from OccupationCounter c where c.id=:id")
}) 


public class OccupationCounter {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "count")
    private int count;
   
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="schedule", referencedColumnName="id")
    @JsonBackReference(value="countOccupation-movement")
    private Schedule schedule;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id")
    @JsonBackReference(value="occupation-movement")
    private Occupation occupation;
    
    /*@ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="course", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
*/
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}

