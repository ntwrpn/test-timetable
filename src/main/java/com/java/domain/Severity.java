
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "severity")
@NamedQueries({
@NamedQuery(name = "Severity.getAll", query = "SELECT c from Severity c"),
@NamedQuery(name = "Severity.getById", query = "SELECT c from Severity c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Severity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="severity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "severity-movement")
    @JsonIgnoreProperties(value = "severity", allowSetters = true)
    @Column(nullable = true)
    private Set<SeveritySubject> severitySubjects;

    public Severity() {
    }

    public Set<SeveritySubject> getSeveritySubjects() {
        return severitySubjects;
    }

    public void setSeveritySubjects(Set<SeveritySubject> severitySubjects) {
        this.severitySubjects = severitySubjects;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

