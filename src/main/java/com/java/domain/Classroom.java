
package com.java.domain;

import javax.persistence.*;

@Entity
@Table(name = "Classroom")

@NamedQueries({
@NamedQuery(name = "Classroom.getAll", query = "SELECT c from Classroom c"),
@NamedQuery(name = "Classroom.getById", query = "SELECT c from Classroom c where c.id=:id")
}) 


public class Classroom {

    
    @Id
    @Column(name = "idclassroom")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="classroom_type_id", referencedColumnName="id", nullable = true)
    private ClassroomType classroom_type_id;

    @Column(name = "classroomc_desc")
    private String classroomc_desc;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="corps_id", referencedColumnName="id", nullable = true)
    private Corps corps_id;

    @Column(name = "count_of_place")
    private int count_of_place;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="lectern_id", referencedColumnName="id", nullable = true)
    private Lectern lectern_id;

    public Corps getCorps_id() {
        return corps_id;
    }

    public void setCorps_id(Corps corps_id) {
        this.corps_id = corps_id;
    }

    public Lectern getLectern_id() {
        return lectern_id;
    }

    public void setLectern_id(Lectern lectern_id) {
        this.lectern_id = lectern_id;
    }

    @Column(name = "name")
    private String name;


    public Classroom() {
    }

    public ClassroomType getClassroom_type_id() {
        return classroom_type_id;
    }

    public void setClassroom_type_id(ClassroomType classroom_type_id) {
        this.classroom_type_id = classroom_type_id;
    }

    


    public String getClassroomc_desc() {
        return classroomc_desc;
    }
    
    public void setClassroomc_desc(String classroomc_desc) {
        this.classroomc_desc=classroomc_desc;
    }



    public int getCount_of_place() {
        return count_of_place;
    }
    
    public void setCount_of_place(int count_of_place) {
        this.count_of_place=count_of_place;
    }



    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

