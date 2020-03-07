
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
@Table(name = "severity")
@NamedQueries({
@NamedQuery(name = "Severity.getAll", query = "SELECT c from Severity c"),
@NamedQuery(name = "Severity.getById", query = "SELECT c from Severity c where c.id=:id")
}) 
public class Severity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="severity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "severity-movement")
    @JsonIgnoreProperties(value = "severity", allowSetters = true)
    @Column(nullable = true)
    private Set<SeveritySubject> severitySubjects;

}

