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

    @Column(name = "name",  length = 1000)
    @Size(max = 1000, message = ValidationMessages.FLOW_NAME_SIZE)
    @NotBlank(message = ValidationMessages.FLOW_NAME_NOT_BLANK)
    private String name;

    @Column(name = "description", length = 10000)
    @Size(max = 10000, message = ValidationMessages.FLOW_DESCRIPTION_SIZE)
    private String description;

    @OneToMany(mappedBy = "flow",cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "flow-group")
    @Column(nullable = true)
    private List<Groups> groups;

    @OneToMany(mappedBy="flow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Timetable> timetable;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "deanery", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Deanery deanery;

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
