
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "occupation")

@NamedQueries({
@NamedQuery(name = "Occupation.getAll", query = "SELECT c from Occupation c"),
@NamedQuery(name = "Occupation.getById", query = "SELECT c from Occupation c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Occupation {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "value")
    private String value;

}

