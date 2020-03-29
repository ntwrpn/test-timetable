
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
public class UserRoles {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "endpoint")
    private String endpoint;

    @ManyToMany(mappedBy="userRoles",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference(value="user-roles")
    private Set<Users> user;
    
    @ManyToMany(mappedBy="userRoles", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference(value="access-roles")
    private List<Access> access;

}

