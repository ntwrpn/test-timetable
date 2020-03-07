
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_roles")

@NamedQueries({
@NamedQuery(name = "UserRoles.getAll", query = "SELECT c from UserRoles c"),
@NamedQuery(name = "UserRoles.getById", query = "SELECT c from UserRoles c where c.user_role_id=:user_role_id")
}) 


public class UserRoles {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy="userRoles",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Users> user;

    
}

