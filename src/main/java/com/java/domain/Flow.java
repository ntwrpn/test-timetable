package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.java.service.GroupsService;
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
@Table(name = "flow")
public class Flow {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "flow", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference(value = "flow-group")
    //@Column(nullable = true)
    private List<Groups> groups =new ArrayList<Groups>();

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty", referencedColumnName = "id", nullable = true)
    //@JsonManagedReference
    private Faculty faculty = null;

    /*public void setGroups(List<Groups> newgroups) {
        if (newgroups != null) {
            if (groups == null) {
                groups = new ArrayList<Groups>();          
            }
            
            for (Groups group : newgroups) {
                group.setFlow(this);
                groups.add(group);
            }
        }
    }*/
}
