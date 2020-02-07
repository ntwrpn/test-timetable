
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_roles")

@NamedQueries({
@NamedQuery(name = "UserRoles.getAll", query = "SELECT c from UserRoles c"),
@NamedQuery(name = "UserRoles.getById", query = "SELECT c from UserRoles c where c.user_role_id=:user_role_id")
}) 


public class UserRoles {

    
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_role_id=0;   
    
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy="userRoles",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Users> username;

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Users> getUsername() {
        return username;
    }

    public void setUsername(Set<Users> username) {
        this.username = username;
    }

    
}

