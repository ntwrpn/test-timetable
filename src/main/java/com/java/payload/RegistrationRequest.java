package com.java.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {
    @NotBlank
    @Size(min=3, max = 60)
    private String username;
 
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String name;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String surname;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String patronymic;

}