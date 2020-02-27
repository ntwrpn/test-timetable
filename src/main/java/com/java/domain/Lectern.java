
package com.java.domain;


import javax.persistence.*;
@Entity
@Table(name = "Lectern")

@NamedQueries({
@NamedQuery(name = "Lectern.getAll", query = "SELECT c from Lectern c"),
@NamedQuery(name = "Lectern.getById", query = "SELECT c from Lectern c where c.id=:id")
}) 


public class Lectern {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="faculty", referencedColumnName="id", nullable = true)
    private Faculty faculty;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="lectern_type_id", referencedColumnName="idlectern_type", nullable = true)
    private LecternType lectern_type_id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public LecternType getLectern_type_id() {
        return lectern_type_id;
    }

    public void setLectern_type_id(LecternType lectern_type_id) {
        this.lectern_type_id = lectern_type_id;
    }

    public Lectern() {
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

