package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

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
@Table(name = "lectern")
public class Lectern {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "deanery", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Deanery deanery;

    @OneToMany(mappedBy = "lectern", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "lectern", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Speciality> specialities;
	
    @OneToMany(mappedBy="lectern", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Flow> flows;

    @Column(name = "fullname", unique = true)
    @Size(min = 3, max = 255, message = ValidationMessages.LECTERN_FULLNAME_SIZE)
    @NotBlank(message = ValidationMessages.LECTERN_FULLNAME_NOT_BLANK)
    private String fullname;

    @Column(name = "name", unique = true)
    @Size(min = 3, max = 50, message = ValidationMessages.LECTERN_NAME_SIZE)
    @NotBlank(message = ValidationMessages.LECTERN_NAME_NOT_BLANK)
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = ValidationMessages.LECTERN_DESCRIPTION_SIZE)
    private String description;
}

