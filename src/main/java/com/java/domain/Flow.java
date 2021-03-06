package com.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "flow", fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference(value = "flow-group")
    @Column(nullable = true)
    private List<Groups> groups;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faculty", referencedColumnName = "id", nullable = true)
    //@JsonManagedReference
    private Faculty faculty = null;

    /*public void setGroups(List<Groups> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
        if (groups != null) {
            for (Groups group : groups) {
                this.groups.add(group);
            }
        }
    }*/
}
