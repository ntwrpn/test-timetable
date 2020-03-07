
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "groups")
@NamedQueries({
@NamedQuery(name = "Groups.getAll", query = "SELECT c from Groups c"),
@NamedQuery(name = "Groups.getById", query = "SELECT c from Groups c where c.id=:id")
}) 
public class Groups {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="flow", referencedColumnName="id", nullable = true)
    @JsonBackReference(value="flow-group")
    private Flow flow=null;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="speciality", referencedColumnName="id", nullable = true)
    private Speciality speciality;  


    @OneToMany(mappedBy="groups", cascade=CascadeType.ALL)
    @JsonBackReference //(value="subgroup-group")
    @Column(nullable = true)
    private List<Subgroup> subgroup;
}