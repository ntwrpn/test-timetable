
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
@Table(name = "Lectern")

@NamedQueries({
@NamedQuery(name = "Lectern.getAll", query = "SELECT c from Lectern c"),
@NamedQuery(name = "Lectern.getById", query = "SELECT c from Lectern c where c.id=:id")
}) 


public class Lectern {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="faculty", referencedColumnName="id", nullable = true)
    private Faculty faculty;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="lectern_type_id", referencedColumnName="id", nullable = true)
    private LecternType lectern_type_id;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public LecternType getLectern_type_id() {
        return lectern_type_id;
    }

    public void setLectern_type_id(LecternType lectern_type_id) {
        this.lectern_type_id = lectern_type_id;
    }


    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
        this.name = name;
    }

    
}

