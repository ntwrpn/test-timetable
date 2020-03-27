
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Lesson")
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "classroom_id")
    private int classroom_id;

    @Column(name = "day_id")
    private int day_id;

    @Column(name = "needed_class_id")
    private int needed_class_id;

    @Column(name = "subject_id")
    private int subject_id;

    @Column(name = "teacher_id")
    private int teacher_id;

    @Column(name = "turn_id")
    private int turn_id;
}

