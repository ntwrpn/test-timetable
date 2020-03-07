
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
@Table(name = "lecterntype")
@NamedQueries({
@NamedQuery(name = "LecternType.getAll", query = "SELECT c from LecternType c"),
@NamedQuery(name = "LecternType.getById", query = "SELECT c from LecternType c where c.id=:id")
}) 
public class LecternType {


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

    @Column(name = "descr")
    private String descr;
    
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
    }

}

