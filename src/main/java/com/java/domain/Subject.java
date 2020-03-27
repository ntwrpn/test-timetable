
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "abbreviation")
    private String abbreviation;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "number_of_discipline")
    private String numberOfDiscipline;
    
    @Column(name = "sum_of_hours")
    private int sumOfHours;
    
    @Column(name = "free_hours")
    private int freeHours;
    
    @Column(name = "position")
    private int position;
    
    @Column(name = "description")
    private String description;

    @Column(name = "is_template")
    private boolean isTemplate;
    
    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private List<Semester> semesters;
    
    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="severity-subject-movement")
    @Column(nullable = true)
    private Set<SeveritySubject> severities;
    
    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="pereodic-severity-subject-movement")
    @Column(nullable = true)
    private Set<PereodicSeveritySubject> pereodicSeverities;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "study_plan_id", nullable = true)
    @JsonBackReference
    private StudyPlan studyPlan;


}

