
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "weekcount")

@NamedQueries({
@NamedQuery(name = "WeekCount.getAll", query = "SELECT c from WeekCount c"),
@NamedQuery(name = "WeekCount.getById", query = "SELECT c from WeekCount c where c.id=:id")
}) 
public class WeekCount {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    
    @Column(name = "count")
    private int count;
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="studyplan", referencedColumnName="id")
    @JsonBackReference(value="studyPlan-week-movement")
    private StudyPlan studyPlan;

    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int colspan) {
        this.count = colspan;
    }
}
