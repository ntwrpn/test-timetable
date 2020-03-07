
package com.java.domain;

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
@Table(name = "speciality")

@NamedQueries({
@NamedQuery(name = "Speciality.getAll", query = "SELECT c from Speciality c"),
@NamedQuery(name = "Speciality.getById", query = "SELECT c from Speciality c where c.id=:id")
}) 


public class Speciality {

    
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

    @Column(name = "descr")
    private String descr;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="lectern", referencedColumnName="id", nullable = true)
    private Lectern lectern;

    @Column(name = "name")
    private String name;



    public Lectern getLectern() {
        return lectern;
    }

    public void setLectern(Lectern lectern) {
        this.lectern = lectern;
    }
    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

