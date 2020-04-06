
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "groups")
public class Groups {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="flow", referencedColumnName="id", nullable = true)
    //@JsonIgnoreProperties(value = {"flow-group"})
    private Flow flow;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  

	@Column(name = "count_of_student")
    private int countOfStudents;
  
}
