
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
@Table(name = "classroomtype")

@NamedQueries({
@NamedQuery(name = "ClassroomType.getAll", query = "SELECT c from ClassroomType c"),
@NamedQuery(name = "ClassroomType.getById", query = "SELECT c from ClassroomType c where c.id=:id")
}) 


public class ClassroomType {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;


    @Column(name = "classroom_typec_desc")
    private String classroom_typec_desc;

}

