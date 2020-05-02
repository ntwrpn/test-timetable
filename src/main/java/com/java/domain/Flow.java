package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.java.service.GroupsService;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Column(name = "name", unique = true)
    @Size(min = 3, max = 100, message = ValidationMessages.FLOW_NAME_SIZE)
    @NotBlank(message = ValidationMessages.FLOW_NAME_NOT_BLANK)
    private String name;

    @Column(name = "description")
    @Size(min = 3, max = 255, message = ValidationMessages.FLOW_DESCRIPTION_SIZE)
    @NotBlank(message = ValidationMessages.FLOW_DESCRIPTION_NOT_BLANK)
    private String description;

    @OneToMany(mappedBy = "flow", cascade = CascadeType.ALL)
    //@JsonManagedReference(value = "flow-group")
    //@Column(nullable = true)
    @JsonIgnore
    private List<Groups> groups;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lectern", referencedColumnName = "id", nullable = true)
    //@JsonManagedReference
    private Lectern lectern;

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
