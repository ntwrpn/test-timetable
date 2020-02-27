
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subject")

@NamedQueries({
@NamedQuery(name = "Subject.getAll", query = "SELECT c from Subject c"),
@NamedQuery(name = "Subject.getById", query = "SELECT c from Subject c where c.id=:id")
}) 


public class Subject {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;

    @Column(name = "name")
    private String name;
    
    @Column(name = "abbreviation")
    private String abbreviation;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "numberOfDiscipline")
    private String numberOfDiscipline;
    
    @Column(name = "sumOfHours")
    private int sumOfHours;
    
    @Column(name = "freeHours")
    private int freeHours;
    
    @Column(name = "position")
    private int position;
    
    @Column(name = "isChanged")
    private boolean isChanged=false;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private List<Semester> semesters;
    
    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<SeveritySubject> severities;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JsonBackReference
    private StudyPlan studyPlan;
    
    public int getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumberOfDiscipline() {
        return numberOfDiscipline;
    }

    public void setNumberOfDiscipline(String numberOfDiscipline) {
        this.numberOfDiscipline = numberOfDiscipline;
    }

    public int getSumOfHours() {
        return sumOfHours;
    }

    public void setSumOfHours(int sumOfHours) {
        this.sumOfHours = sumOfHours;
    }

    public int getFreeHours() {
        return freeHours;
    }

    public void setFreeHours(int freeHours) {
        this.freeHours = freeHours;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIsChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public Set<SeveritySubject> getSeverities() {
        return severities;
    }

    public void setSeverities(Set<SeveritySubject> severities) {
        this.severities = severities;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }


    public Subject() {
    }

    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }


}

