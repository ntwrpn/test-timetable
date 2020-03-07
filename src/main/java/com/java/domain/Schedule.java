
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name = "schedule")

@NamedQueries({
@NamedQuery(name = "Schedule.getAll", query = "SELECT c from Schedule c"),
@NamedQuery(name = "Schedule.getById", query = "SELECT c from Schedule c where c.id=:id")
}) 


public class Schedule {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="courses-movement")
    private List<Course> courses;
       
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="countOccupation-movement")
    private List<OccupationCounter> countOccupation;

}

