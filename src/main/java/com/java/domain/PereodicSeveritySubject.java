
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pereodicseveritysubject")
@NamedQueries({
@NamedQuery(name = "PereodicSeveritySubject.getAll", query = "SELECT c from PereodicSeveritySubject c"),
@NamedQuery(name = "PereodicSeveritySubject.getById", query = "SELECT c from PereodicSeveritySubject c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PereodicSeveritySubject {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="pereodic_severity_id")
//    @JsonBackReference(value = "pereodic-severity-movement")
    @JsonIgnoreProperties(value = "pereodicSeveritySubjects", allowSetters = true)
    private PereodicSeverity pereodicSeverity;

    @OneToMany(mappedBy="pereodicSeveritySubject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "semester-numbers-movement")
    private Set<SemesterNumber> semesterNumbers;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonBackReference(value = "pereodic-severity-subject-movement")
    private Subject subject;
    

    public PereodicSeveritySubject() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PereodicSeverity getPereodicSeverity() {
        return pereodicSeverity;
    }

    public void setPereodicSeverity(PereodicSeverity pereodicSeverity) {
        this.pereodicSeverity = pereodicSeverity;
    }

    public Set<SemesterNumber> getSemesterNumbers() {
        return semesterNumbers;
    }

    public void setSemesterNumbers(Set<SemesterNumber> semesterNumbers) {
        this.semesterNumbers = semesterNumbers;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

