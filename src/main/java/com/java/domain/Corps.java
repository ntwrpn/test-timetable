
package com.java.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Corps")
@NamedQueries({
@NamedQuery(name = "Corps.getAll", query = "SELECT c from Corps c"),
@NamedQuery(name = "Corps.getById", query = "SELECT c from Corps c where c.id=:id")
}) 
public class Corps {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

}

