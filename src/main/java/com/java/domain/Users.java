
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
@NamedQuery(name = "Users.getByName", query = "SELECT c from Users c where c.username=:username")
}) 


public class Users {

    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled = false;   
    
    @OneToMany(mappedBy="username", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonIgnoreProperties("syllabus")
    @JsonManagedReference
    @Column(nullable = true)
    private Set<UserRoles> role=null;

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

    

}

