
package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Column(name = "description", length = 10000)
    @Size(max = 10000, message = ValidationMessages.DESCRIPTION_SIZE_LENGTH)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lectern_id")
    @JsonIgnore
    private Lectern lectern;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudyPlan> studyPlans;

    @Column(name = "name", length = 1000)
    @Size(max = 1000, message = ValidationMessages.NAME_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    @Column(name = "abbreviation")
    @Size(max = 255, message = ValidationMessages.ABBREVIATION_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.ABBREVIATION_NOT_BLANK)
    private String abbreviation;
}

