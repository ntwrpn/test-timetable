package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({ "id", "name", "status", "registerNumber", "flow", "statusApplyDate","registerNumberApplyDate" })
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")
    @Column(name = "status_apply_date")
    private Date statusApplyDate;

    @Column(name = "register_number")
    private int registerNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")
    @Column(name = "register_number_apply_date")
    private Date registerNumberApplyDate;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "flow", referencedColumnName = "id", nullable = true)
    @JsonIgnoreProperties({"lectern"})
    private Flow flow;
    
    @OneToMany(mappedBy="timetable", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private List<Lesson> lesson;
    
    @JsonProperty("lesson")
    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }
    
    @JsonIgnore
    public List<Lesson> getLesson() {
        return this.lesson;
    }

    

    public void setStatus(StudyPlanStatus status) {
        if (status != this.getStatus()) {
            this.status = status;
            this.statusApplyDate = new Date();
        }
    }

    public void setRegisterNumber(int registerNumber) {
        if (registerNumber != this.getRegisterNumber()) {
            this.registerNumber = registerNumber;
            this.registerNumberApplyDate = new Date();
        }
    }

}
