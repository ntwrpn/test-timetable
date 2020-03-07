
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "semeseternumber")

@NamedQueries({
@NamedQuery(name = "SemesterNumber.getAll", query = "SELECT c from SemesterNumber c"),
@NamedQuery(name = "SemesterNumber.getById", query = "SELECT c from SemesterNumber c where c.id=:id")
}) 
public class SemesterNumber {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "number")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="pereodic_severity_subject_id", nullable = true)
    @JsonBackReference(value="semester-numbers-movement")
    private PereodicSeveritySubject pereodicSeveritySubject;

    
    
}
