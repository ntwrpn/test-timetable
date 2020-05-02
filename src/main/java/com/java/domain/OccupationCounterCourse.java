
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "occupationcountercourse")
public class OccupationCounterCourse {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "count")
    @NotNull
    @Range(min = 1)
    private int count;
   
    /*@ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="schedule", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Schedule schedule;*/

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id")
    private Occupation occupation;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="course", referencedColumnName="id")
    @JsonBackReference(value="course-movement")
    private Course course;


}

