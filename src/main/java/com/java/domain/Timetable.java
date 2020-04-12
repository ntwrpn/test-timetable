
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StudyPlanStatus status;

    @Column(name = "status_apply_date")
    private Date statusApplyDate;

    @Column(name = "register_number")
    private int registerNumber;

    @Column(name = "register_number_apply_date")
    private Date registerNumberApplyDate;

}

