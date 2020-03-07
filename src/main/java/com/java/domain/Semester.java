
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Semester")

@NamedQueries({
@NamedQuery(name = "Semester.getAll", query = "SELECT c from Semester c"),
@NamedQuery(name = "Semester.getById", query = "SELECT c from Semester c where c.id=:id")
}) 


public class Semester {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "number")
    private int number;

    @Column(name = "hoursPerWeek")
    private int hoursPerWeek;
    
    @Column(name = "creditUnits")
    private int creditUnits;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Subject subject;

    

}

