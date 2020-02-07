
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")

@NamedQueries({
@NamedQuery(name = "Users.getAll", query = "SELECT c from Users c"),
@NamedQuery(name = "Users.getByName", query = "SELECT c from Users c where c.username=:username"),
@NamedQuery(name = "Users.getByEnabled", query = "SELECT c from Users c where c.enabled=:enabled")

}) 


public class Users {

    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled = false;   
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="username", referencedColumnName="username")
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "username", nullable = false, updatable = false) },
     inverseJoinColumns = { @JoinColumn(name = "user_role_id", nullable = false, updatable = false) })
    @Column(name = "role")
    //@JsonManagedReference
    private Set<UserRoles> userRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Users() {
    }
    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> role) {
        this.userRoles = role;
    }
    

}

