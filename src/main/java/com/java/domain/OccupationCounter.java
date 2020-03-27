
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
@Table(name = "occupationcounter")
public class OccupationCounter {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "count")
    private int count;
   
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="schedule", referencedColumnName="id")
    @JsonBackReference(value="countOccupation-movement")
    private Schedule schedule;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id", nullable = true)
    private Occupation occupation;

}

