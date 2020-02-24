
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "studyplan")

@NamedQueries({
@NamedQuery(name = "StudyPlan.getAll", query = "SELECT c from StudyPlan c"),
@NamedQuery(name = "StudyPlan.getById", query = "SELECT c from StudyPlan c where c.id=:id")
}) 



public class StudyPlan {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<Subject> subject;
       
    @Column(name = "coefficient")
    private int coefficient;

    @Column(name = "countOfSem")
    private int countOfSem;  

    @OneToMany(mappedBy="studyPlan", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference(value="studyPlan-week-movement")
    private Set<Week> weeks;
    
    @Column(name = "isChanged")
    private boolean isChanged=false;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StudyPlanStatus status;  
    
    @Column(name = "statusApplyDate")
    private Date statusApplyDate;  
    
    @Column(name = "registerNumber")
    private int registerNumber; 
    
    @Column(name = "registerNumberApplyDate")
    private Date registerNumberApplyDate;  

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "educationForm")
    private EducationForm educationForm;  

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

    public Set<Subject> getSubject() {
        return subject;
    }

    public void setSubject(Set<Subject> subject) {
        this.subject = subject;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCountOfSem() {
        return countOfSem;
    }

    public void setCountOfSem(int countOfSem) {
        this.countOfSem = countOfSem;
    }

    public Set<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(Set<Week> weeks) {
        this.weeks = weeks;
    }

    public boolean isIsChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public StudyPlanStatus getStatus() {
        return status;
    }

    public void setStatus(StudyPlanStatus status) {
        this.status = status;
    }

    public Date getStatusApplyDate() {
        return statusApplyDate;
    }

    public void setStatusApplyDate(Date statusApplyDate) {
        this.statusApplyDate = statusApplyDate;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Date getRegisterNumberApplyDate() {
        return registerNumberApplyDate;
    }

    public void setRegisterNumberApplyDate(Date registerNumberApplyDate) {
        this.registerNumberApplyDate = registerNumberApplyDate;
    }

    public EducationForm getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForm educationForm) {
        this.educationForm = educationForm;
    }
    
}

