
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "weekcount")
public class WeekCount {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
	
	@Column(name = "position")
    @NotNull
    private Integer position;

    @Column(name = "count")
    @Range(min = 1, max = 20, message = ValidationMessages.WEEK_COUNT_SIZE)
    @NotNull(message = ValidationMessages.WEEK_COUNT_NOT_BLANK)
    private Integer count;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="studyplan", referencedColumnName="id")
    @JsonBackReference(value="studyPlan-week-movement")
    private StudyPlan studyPlan;

}
