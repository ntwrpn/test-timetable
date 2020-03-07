
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
@Table(name = "week")
@NamedQueries({
@NamedQuery(name = "Week.getAll", query = "SELECT c from Week c"),
@NamedQuery(name = "Week.getById", query = "SELECT c from Week c where c.id=:id")
}) 
public class Week {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "colspan")
    private int colspan;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="occupation", referencedColumnName="id")
	private Occupation occupation;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="studyplan", referencedColumnName="id")
    @JsonBackReference(value="studyPlan-week-movement")
    private StudyPlan studyPlan;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="course", referencedColumnName="id", nullable = true)
    @JsonBackReference(value="course-weeks-movement")
    private Course course;

}

