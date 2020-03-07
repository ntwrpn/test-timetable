package com.java.dto;

import com.java.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorpsDTO {
    private UUID id;
    @NotNull
    @Size(max = 40, message = "Max length of name is 40 symbols")
    private String name;
    @Size(max=150, message = "Street is too big. It should be less then 150 symbols")
    private String street;
}
