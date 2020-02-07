
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.java.repository.CorpsService;

@Entity
@Table(name = "faculty")

@NamedQueries({
@NamedQuery(name = "Faculty.getAll", query = "SELECT c from Faculty c"),
@NamedQuery(name = "Faculty.getById", query = "SELECT c from Faculty c where c.id=:id")
}) 


public class Faculty {

    
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

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="corps", referencedColumnName="id", nullable = true)
    private Corps corps=null;

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="faculty", cascade = CascadeType.ALL)
    @JsonBackReference
    @Column(nullable = true)
    private Set<Flow> flow;

    public Set<Flow> getFlow() {
        return flow;
    }

    public void setFlow(Set<Flow> flow) {
        this.flow = flow;
    }


    public Faculty() {
    }

    

    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname=fullname;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

