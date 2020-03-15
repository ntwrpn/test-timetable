
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="courses-movement")
    private List<Course> courses;
       
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="countOccupation-movement")
    private List<OccupationCounter> countOccupation;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="study_plan", referencedColumnName="id", nullable = true)
    @JsonBackReference(value="schedule-movement")
    private StudyPlan studyPlan;
}

