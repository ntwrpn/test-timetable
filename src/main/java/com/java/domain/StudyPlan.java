
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "schedule-movement")
    private List<Schedule> schedules;

    @Column(name = "coefficient")
    @Range(min = 1, max = 15, message = ValidationMessages.COEFFICIENT_SIZE)
    @NotNull(message = ValidationMessages.COEFFICIENT_NOT_BLANK)
    private Integer coefficient;

    @Column(name = "count_of_sem")
    @Range(min = 1, max = 12, message = ValidationMessages.COUNT_OF_SEM_SIZE)
    @NotNull(message = ValidationMessages.COUNT_OF_SEM_NOT_BLANK)
    private Integer countOfSem;

    @OneToMany(mappedBy="studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="studyPlan-week-movement")
    @Column(nullable = true)
    private List<WeekCount> weeks;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "speciality_id", nullable = true)
    @JsonIgnoreProperties(value = {"description", "lectern", "studyPlan"})
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
    @Column(name = "education_forme")
    private EducationForm educationForm;
}

