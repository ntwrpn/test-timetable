
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

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    @JsonBackReference
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled = false;   
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@JoinColumn(name="username", referencedColumnName="username")
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "users", nullable = false, updatable = false)},
     inverseJoinColumns = { @JoinColumn(name = "user_role_id", nullable = false, updatable = false)})
    @Column(name = "role")
    //@JsonIdentityReference(alwaysAsId = true)
    //@JsonManagedReference
    @JsonIgnoreProperties("username")
    private List<UserRoles> userRoles;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    private Teacher teacher;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
    
}

