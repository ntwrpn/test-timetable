
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flow")
@NamedQueries({
@NamedQuery(name = "Flow.getAll", query = "SELECT c from Flow c"),
@NamedQuery(name = "Flow.getById", query = "SELECT c from Flow c where c.id=:id")
}) 
public class Flow {

    
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

    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy="flow", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="flow-group")
    @Column(nullable = true)
    private Set<Groups> groups;
    
    
    @ManyToOne(optional=true, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="faculty", referencedColumnName="id", nullable = true)
    //@JsonManagedReference
    private Faculty faculty=null;

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }


    public Flow() {
    }

    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

