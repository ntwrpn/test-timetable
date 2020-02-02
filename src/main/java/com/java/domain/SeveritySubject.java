
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SeveritySubject")
@NamedQueries({
@NamedQuery(name = "SeveritySubject.getAll", query = "SELECT c from SeveritySubject c"),
@NamedQuery(name = "SeveritySubject.getById", query = "SELECT c from SeveritySubject c where c.id=:id")
}) 
public class SeveritySubject {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "severity")
    private int severity;

    @Column(name = "subjectId")
    private int subjectId;

    @Column(name = "hours")
    private int hours;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JsonBackReference
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}

