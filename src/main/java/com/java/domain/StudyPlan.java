
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "studyplan")
public class StudyPlan {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private List<Subject> subjects;
       
    @Column(name = "coefficient")
    private int coefficient;

    @Column(name = "count_of_sem")
    private int countOfSem;  

    @OneToMany(mappedBy="studyPlan", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference(value="studyPlan-week-movement")
    @Column(nullable = true)
    private List<WeekCount> weeks;
    
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StudyPlanStatus status;  
    
    @Column(name = "status_apply_date")
    private Date statusApplyDate;  
    
    @Column(name = "register_number")
    private int registerNumber; 
    
    @Column(name = "register_number_apply_date")
    private Date registerNumberApplyDate;  

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "education_form")
    private EducationForm educationForm;  
    
}

