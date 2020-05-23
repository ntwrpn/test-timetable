
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
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

    @Column(name = "name", length = 1000)
    @Size(max = 1000, message = ValidationMessages.NAME_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    @Column(name = "abbreviation")
    @Size(max = 255, message = ValidationMessages.ABBREVIATION_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.ABBREVIATION_NOT_BLANK)
    private String abbreviation;

    @Column(name = "department")
    @Size(max = 255, message = ValidationMessages.ABBREVIATION_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.ABBREVIATION_NOT_BLANK)
    private String department;

    @Column(name = "sum_of_hours")
    private Integer sumOfHours;

    @Column(name = "free_hours")
    private Integer freeHours;

    @Column(name = "position")
    private Integer position;

    @Column(name = "description", length = 10000)
    @Size(max = 10000, message = ValidationMessages.DESCRIPTION_SIZE_LENGTH)
    private String description;

    @Column(name = "is_template")
    @NotNull(message = ValidationMessages.TEMPLATE_NOT_NULL)
    private Boolean template;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column
    private List<Semester> semesters;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "severity-subject-movement")
    @Column
    private Set<SeveritySubject> severities;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "pereodic-severity-subject-movement")
    @Column
    private Set<PereodicSeveritySubject> pereodicSeverities;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "study_plan_id")
    @JsonBackReference
    private StudyPlan studyPlan;

}

