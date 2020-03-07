
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
@Table(name = "learningseveritylist")

@NamedQueries({
@NamedQuery(name = "LearningSeverityList.getAll", query = "SELECT c from LearningSeverityList c"),
@NamedQuery(name = "LearningSeverityList.getById", query = "SELECT c from LearningSeverityList c where c.id=:id"),
@NamedQuery(name = "LearningSeverityList.getLastId", query = "SELECT c from LearningSeverityList c order by id desc")
}) 


public class LearningSeverityList {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    //@JsonIgnoreProperties("plans_id")
    @JsonBackReference
    private Syllabus syllabus;



    @ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="name_id", referencedColumnName="id", nullable = true)
    private LearningSeverity name_id;



    @Column(name = "hours")
    private int hours;



}

