
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
@Table(name = "Semester")
public class Semester {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "number")
    @NotBlank
    private int number;

    @Column(name = "hours_per_Week")
    @NotNull
    @Range(min = 1)
    private int hoursPerWeek;
    
    @Column(name = "credit_units")
    @NotNull
    @Range(min = 1)
    private int creditUnits;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Subject subject;
}

