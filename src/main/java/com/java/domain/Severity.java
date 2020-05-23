
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "severity")
public class Severity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "name", length = 1000)
    @Size(max = 1000, message = ValidationMessages.NAME_SIZE_LENGTH)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;
    
    @OneToMany(mappedBy="severity", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "severity", allowSetters = true)
    @Column
    private Set<SeveritySubject> severitySubjects;

}

