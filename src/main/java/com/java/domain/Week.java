
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "week")
@NamedQueries({
@NamedQuery(name = "Week.getAll", query = "SELECT c from Week c"),
@NamedQuery(name = "Week.getById", query = "SELECT c from Week c where c.id=:id")
}) 
public class Week {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "colspan")
    private int colspan;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id", nullable = true)
    private Occupation occupation;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JsonBackReference
    private StudyPlan studyPlan;

    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }


    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JsonBackReference
    private Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

}

