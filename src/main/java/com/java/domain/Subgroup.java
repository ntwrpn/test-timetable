
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subgroup")
@NamedQueries({
@NamedQuery(name = "Subgroup.getAll", query = "SELECT c from Subgroup c"),
@NamedQuery(name = "Subgroup.getById", query = "SELECT c from Subgroup c where c.id=:id")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

public class Subgroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "count_of_student")
    private int count_of_student;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="groups", referencedColumnName="id")
    //@JsonManagedReference(value="subgroup-group")
    private Groups groups=null;


    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    
    public int getCount_of_student() {
        return count_of_student;
    }
    
    public void setCount_of_student(int count_of_student) {
        this.count_of_student=count_of_student;
    }
}