
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import java.util.Set;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "syllabus")

@NamedQueries({
@NamedQuery(name = "Syllabus.getAll", query = "SELECT c from Syllabus c"),
@NamedQuery(name = "Syllabus.getById", query = "SELECT c from Syllabus c where c.id=:id")
}) 


public class Syllabus {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    
    //@ElementCollection(targetClass=Integer.class)
    //@Column(name = "plans_id")
    //private List<Integer> plans_id;

    @OneToMany(mappedBy="syllabus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<LearningSeverityList> plans_id;

    public Set<LearningSeverityList> getPlans_id() {
        return plans_id;
    }

    public void setPlans_id(Set<LearningSeverityList> plans_id) {
        this.plans_id = plans_id;
    }


    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }
/*
    public List<Integer> getPlans_id() {
        return plans_id;
    }
    
    public void setPlans_id(List<Integer> plans_id) {
        this.plans_id=plans_id;
    }*/


}

