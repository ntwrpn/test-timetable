
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

@NamedQueries({
@NamedQuery(name = "OccupationCounter.getAll", query = "SELECT c from OccupationCounter c"),
@NamedQuery(name = "OccupationCounter.getById", query = "SELECT c from OccupationCounter c where c.id=:id")
}) 


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
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="occupation", referencedColumnName="id")
    @JsonBackReference(value="occupation-movement")
    private Occupation occupation;
    
    /*@ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="course", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
*/

}

