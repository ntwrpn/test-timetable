package com.java.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
  @NotBlank
    @Size(min=3, max = 60)
    private String username;
 
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
 
}