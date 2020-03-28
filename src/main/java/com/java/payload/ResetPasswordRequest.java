package com.java.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordRequest {

    @NotBlank
    private UUID id;

    @NotBlank
    @Size(max = 40)
    private String token;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;


}
