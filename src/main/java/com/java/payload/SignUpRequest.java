package com.java.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String login;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}