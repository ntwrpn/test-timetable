
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "descr")
    private String descr;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="lectern", referencedColumnName="id", nullable = true)
    @JsonIgnore
    private Lectern lectern;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudyPlan> studyPlans;

    @Column(name = "name")
    private String name;
}

