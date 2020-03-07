
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "course")

@NamedQueries({
@NamedQuery(name = "Course.getAll", query = "SELECT c from Course c"),
@NamedQuery(name = "Course.getById", query = "SELECT c from Course c where c.id=:id")
}) 
public class Course {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "total")
    private int total;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="course", fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, orphanRemoval = true)
    @JsonManagedReference(value="course-weeks-movement")
    @Column(nullable = false)
    //@JoinColumn(name="course", nullable=false)
    private List<Week> weeks;
       
    @OneToMany(mappedBy="course", cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="course-movement")
    private List<OccupationCounterCourse> countOccupation;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="schedule", referencedColumnName="id", nullable = true)
    @JsonBackReference(value="courses-movement")
    private Schedule schedule;

    public void setWeek(List<Week> weeks) {
        this.weeks.clear();
        for (Week week:weeks) {
            week.setCourse(this);
        }
        this.weeks = weeks;
    }

    public List<OccupationCounterCourse> getCountOccupation() {
        return countOccupation;
    }

    public void setCountOccupation(List<OccupationCounterCourse> countOccupation) {
        this.countOccupation = countOccupation;
    }


}

