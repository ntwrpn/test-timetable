
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
@Table(name = "learningseverity")

@NamedQueries({
@NamedQuery(name = "LearningSeverity.getAll", query = "SELECT c from LearningSeverity c"),
@NamedQuery(name = "LearningSeverity.getById", query = "SELECT c from LearningSeverity c where c.id=:id")
}) 


public class LearningSeverity {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;


    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

