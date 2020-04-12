
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
@Table(name = "classroom")
public class Classroom {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    

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

    @Column(name = "name")
    private String name;

}

