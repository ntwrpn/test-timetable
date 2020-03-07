
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
@Table(name = "typeoflesson")

@NamedQueries({
@NamedQuery(name = "TypeOfLesson.getAll", query = "SELECT c from TypeOfLesson c"),
@NamedQuery(name = "TypeOfLesson.getById", query = "SELECT c from TypeOfLesson c where c.id=:id")
}) 


public class TypeOfLesson {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "type_of_lesson")
    private String type_of_lesson;



}

