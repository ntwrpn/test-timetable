
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "pereodicseverity")
public class PereodicSeverity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="pereodicSeverity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "pereodic-severity-movement")
    @JsonIgnoreProperties(value = "pereodicSeverity", allowSetters = true)
    @Column(nullable = true)
    private Set<PereodicSeveritySubject> pereodicSeveritySubjects;

}

