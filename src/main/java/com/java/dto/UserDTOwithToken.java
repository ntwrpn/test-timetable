
package com.java.dto;

import com.java.domain.*;
import com.java.payload.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTOwithToken {
    private UUID id;
    private String login;
    private String hashedPassword;
    private String email;
    private boolean nonBlock;
    private Collection<UserRoles> roles;
    private JwtAuthenticationResponse token;
}
