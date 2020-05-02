
package com.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.config.ValidationMessages;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "occupation")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Occupation {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "symbol", unique = true)
    @Size(min = 1, max = 3, message = ValidationMessages.OCCUPATION_SYMBOL_SIZE)
    @NotBlank(message = ValidationMessages.OCCUPATION_SYMBOL_NOT_BLANK)
    private String symbol;

    @Column(name = "value", unique = true)
    @Size(min = 3, max = 255, message = ValidationMessages.OCCUPATION_VALUE_SIZE)
    @NotBlank(message = ValidationMessages.OCCUPATION_VALUE_NOT_BLANK)
    private String value;
}

