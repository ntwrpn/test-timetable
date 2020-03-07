
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pereodicseverity")
@NamedQueries({
@NamedQuery(name = "PereodicSeverity.getAll", query = "SELECT c from PereodicSeverity c"),
@NamedQuery(name = "PereodicSeverity.getById", query = "SELECT c from PereodicSeverity c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PereodicSeverity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="pereodicSeverity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "pereodic-severity-movement")
    @JsonIgnoreProperties(value = "pereodicSeverity", allowSetters = true)
    @Column(nullable = true)
    private Set<PereodicSeveritySubject> pereodicSeveritySubjects;

    public PereodicSeverity() {
    }

    public Set<PereodicSeveritySubject> getPereodicSeveritySubjects() {
        return pereodicSeveritySubjects;
    }

    public void setPereodicSeveritySubjects(Set<PereodicSeveritySubject> pereodicSeveritySubjects) {
        this.pereodicSeveritySubjects = pereodicSeveritySubjects;
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

