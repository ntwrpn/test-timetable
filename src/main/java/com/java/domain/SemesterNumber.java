
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "semeseternumber")

@NamedQueries({
@NamedQuery(name = "SemesterNumber.getAll", query = "SELECT c from SemesterNumber c"),
@NamedQuery(name = "SemesterNumber.getById", query = "SELECT c from SemesterNumber c where c.id=:id")
}) 
public class SemesterNumber {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "number")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="pereodic_severity_subject_id", nullable = true)
    @JsonBackReference(value="semester-numbers-movement")
    private PereodicSeveritySubject pereodicSeveritySubject;
    
    public SemesterNumber() {
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
    
    
}
