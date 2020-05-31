
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/*
id: divs.id,
name: name,
groups: group,
subject: subject,
type: type_name,
day: parseInt(day),
time: time,
corps: corps,
classroom: classroom,
teacher: teacher
*/


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="classroom", referencedColumnName="id", nullable = true)
    @JsonIgnoreProperties({"classroom_type","corps","description","description","count_of_place","corps","lectern"})
    private Classroom classroom;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="corps_id", referencedColumnName="id", nullable = true)
    private Corps corps;

    @Column(name = "day")
    private int day;

    @Column(name = "name")
    private String name;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="subject", referencedColumnName="id", nullable = true)
    @JsonIgnoreProperties({"department","sumOfHours","freeHours","position","template","description","semesters","severities","pereodicSeverities"})
    private Subject subject;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "time")
    private String time;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="teacher", referencedColumnName="id", nullable = true)
    @JsonIgnoreProperties({"user","rate","staffType","additionalInfo","academicDegreeAbbreviation","academicDegree","academicRank","lectern"})
    private Teacher teacher;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="timetable", referencedColumnName="id", nullable = true)
    @JsonBackReference
    private Timetable timetable;
   
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "group_lessons", joinColumns = { @JoinColumn(name = "lesson_id", nullable = false, updatable = true)},
     inverseJoinColumns = { @JoinColumn(name = "groups_id", nullable = false, updatable = true)})
    @Column(name = "groups")
    @JsonIgnoreProperties(value={"speciality","countOfStudents","description"})
    private List<Groups> groups;
}

