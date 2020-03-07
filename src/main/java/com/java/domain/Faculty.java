
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "faculty")

@NamedQueries({
@NamedQuery(name = "Faculty.getAll", query = "SELECT c from Faculty c"),
@NamedQuery(name = "Faculty.getById", query = "SELECT c from Faculty c where c.id=:id")
}) 


public class Faculty {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;


    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="corps", referencedColumnName="id", nullable = true)
    private Corps corps=null;


    @Column(name = "fullname")
    private String fullname;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy="faculty", cascade = CascadeType.ALL)
    @JsonBackReference
    @Column(nullable = true)
    private Set<Flow> flow;

}

