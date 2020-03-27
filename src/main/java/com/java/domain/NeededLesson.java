
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
@Table(name = "neededlesson")
public class NeededLesson {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "speciality_id")
    private int speciality_id;

    @Column(name = "time")
    private int time;

    @Column(name = "type_of_lesson_id")
    private int type_of_lesson_id;

}

