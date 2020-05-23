
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pereodicseveritysubject")
public class PereodicSeveritySubject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pereodic_severity_id")
    @JsonIgnoreProperties(value = "pereodicSeveritySubjects", allowSetters = true)
    private PereodicSeverity pereodicSeverity;

    @OneToMany(mappedBy = "pereodicSeveritySubject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "semester-numbers-movement")
    private Set<SemesterNumber> semesterNumbers;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    @JsonBackReference(value = "pereodic-severity-subject-movement")
    private Subject subject;
}

