
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "severitysubject")
@NamedQueries({
@NamedQuery(name = "SeveritySubject.getAll", query = "SELECT c from SeveritySubject c"),
@NamedQuery(name = "SeveritySubject.getById", query = "SELECT c from SeveritySubject c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeveritySubject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="severity_id", nullable = true)
    @JsonIgnoreProperties(value = "severitySubjects", allowSetters = true)
//    @JsonBackReference(value = "severity-movement")
    private Severity severity;

    @Column(name = "hours")
    private int hours;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonBackReference(value = "severity-subject-movement")
    private Subject subject;

    public SeveritySubject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }  
}

