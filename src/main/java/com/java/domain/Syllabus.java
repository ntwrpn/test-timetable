
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "syllabus")

@NamedQueries({
@NamedQuery(name = "Syllabus.getAll", query = "SELECT c from Syllabus c"),
@NamedQuery(name = "Syllabus.getById", query = "SELECT c from Syllabus c where c.id=:id")
}) 


public class Syllabus {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
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


    public Syllabus() {
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

