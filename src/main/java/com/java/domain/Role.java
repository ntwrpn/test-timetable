
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
@Table(name = "Role")

@NamedQueries({
@NamedQuery(name = "Role.getAll", query = "SELECT c from Role c"),
@NamedQuery(name = "Role.getById", query = "SELECT c from Role c where c.id=:id")
}) 


public class Role {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "acess")
    private int acess;

    @Column(name = "type")
    private String type;


}

