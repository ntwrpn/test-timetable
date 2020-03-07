
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
@NamedQueries({
@NamedQuery(name = "Users.getById", query = "SELECT c from Users c where c.id=:id"),
@NamedQuery(name = "Users.getAll", query = "SELECT c from Users c"),
@NamedQuery(name = "Users.getByName", query = "SELECT c from Users c where c.username=:username"),
@NamedQuery(name = "Users.getByEnabled", query = "SELECT c from Users c where c.enabled=:enabled")
}) 
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
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="username", referencedColumnName="username")
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "users", nullable = false, updatable = false)},
     inverseJoinColumns = { @JoinColumn(name = "user_role_id", nullable = false, updatable = false)})
    @Column(name = "role")
    //@JsonIdentityReference(alwaysAsId = true)
    //@JsonManagedReference
    @JsonIgnoreProperties("username")
    private Set<UserRoles> userRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> role) {
        this.userRoles = role;
    }
    

}

