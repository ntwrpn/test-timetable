
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name = "groups")
public class Groups {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    

    @Column(name = "name", unique = true)
    @Size(min = 3, max = 50, message = ValidationMessages.GROUP_NAME_SIZE)
    @NotBlank(message = ValidationMessages.GROUP_NAME_NOT_BLANK)
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = ValidationMessages.GROUP_DESCRIPTION_SIZE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="flow", referencedColumnName="id", nullable = true)
    //@JsonIgnoreProperties(value = {"flow-group"})
    private Flow flow;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  

	@Column(name = "count_of_student")
    @Range(min = 5, max = 40, message = ValidationMessages.GROUP_COUNT_STUDENTS_SIZE)
    @NotNull(message = ValidationMessages.GROUP_COUNT_STUDENTS_NOT_BLANK)
    private Integer countOfStudents;
  
}
