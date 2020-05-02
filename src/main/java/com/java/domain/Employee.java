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
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="deanery_id")
    @JsonIgnore
    private Deanery deanery;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = ValidationMessages.NAME_SIZE)
    @NotBlank(message = ValidationMessages.NAME_EMPLOYEE_NOT_BLANK)
    private String name;

    @Column(name = "patronymic")
    @Size(min = 2, max = 50, message = ValidationMessages.PATRONYMIC_SIZE)
    @NotBlank(message = ValidationMessages.PATRONYMIC_EMPLOYEE_NOT_BLANK)
    private String patronymic;

    @Column(name = "surname")
    @Size(min = 2, max = 100, message = ValidationMessages.SURNAME_SIZE)
    @NotBlank(message = ValidationMessages.SURNAME_EMPLOYEE_NOT_BLANK)
    private String surname;

    @Column(name = "rank")
    @Size(min = 3, max = 100, message = ValidationMessages.RANK_SIZE)
    @NotBlank(message = ValidationMessages.RANK_EMPLOYEE_NOT_BLANK)
    private String rank;
    
    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private Users user;
}