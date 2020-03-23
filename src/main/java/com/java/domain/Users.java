
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;
    
    @Column(name = "password")
    @JsonBackReference
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled = false;   
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinColumn(name="username", referencedColumnName="username")
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "users", nullable = false, updatable = false)},
     inverseJoinColumns = { @JoinColumn(name = "user_role_id", nullable = false, updatable = false)})
    @Column(name = "role")
    //@JsonIdentityReference(alwaysAsId = true)
    //@JsonManagedReference
    @JsonIgnoreProperties("username")
    private Set<UserRoles> userRoles;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name="deanery", referencedColumnName="id", nullable = true)
    private Deanery deanery;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name="lectern", referencedColumnName="id", nullable = true)
    private Lectern lectern;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    private Teacher teacher;
    
    
    public UUID getLectern(){
        if (lectern!=null){
            return lectern.getId();
        }
        else{
            return null;
        }
    }
    
    public UUID getDeanery(){
        if (deanery!=null){
            return deanery.getId();
        }
        else{
            return null;
        }
    }

}

