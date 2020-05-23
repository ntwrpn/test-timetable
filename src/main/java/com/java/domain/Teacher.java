
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lectern_id")
    private Lectern lectern;

    @Column(name = "name", length = 1000)
    @Size(max = 1000, message = ValidationMessages.NAME_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    @Column(name = "patronymic", length = 1000)
    @Size(max = 1000, message = ValidationMessages.PATRONYMIC_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.PATRONYMIC_NOT_BLANK)
    private String patronymic;

    @Column(name = "surname", length = 1000)
    @Size(max = 1000, message = ValidationMessages.SURNAME_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.SURNAME_NOT_BLANK)
    private String surname;

    @Column(name = "position", length = 1000)
    @Size(max = 1000, message = ValidationMessages.POSITION_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.POSITION_NOT_BLANK)
    private String position;

    @Column(name = "academic_rank", length = 1000)
    @Size(max = 1000, message = ValidationMessages.ACADEMIC_RANK_SIZE)
    private String academicRank;

    @Column(name = "academic_degree", length = 1000)
    @Size(max = 1000, message = ValidationMessages.ACADEMIC_DEGREE_SIZE)
    private String academicDegree;

    @Column(name = "academic_degree_abbreviation")
    @Size(max = 255, message = ValidationMessages.ACADEMIC_DEGREE_ABBREVIATION_SIZE)
    private String academicDegreeAbbreviation;

    @Column(name = "additional_info", length = 10000)
    @Size(max = 10000, message = ValidationMessages.ADDITIONAL_INFO_SIZE)
    private String additionalInfo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "staff_type")
    @NotNull(message = ValidationMessages.STAFF_TYPE_NOT_NULL)
    private StaffType staffType;

    @Column (name = "rate")
    @Range(max = 9999, message = ValidationMessages.RATE_RANGE)
    private Double rate;

    @OneToOne(mappedBy = "teacher")
    @JsonIgnoreProperties("teacher")
    private Users user;
}

