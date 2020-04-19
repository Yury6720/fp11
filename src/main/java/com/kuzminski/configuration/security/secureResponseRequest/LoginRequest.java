package com.kuzminski.configuration.security.secureResponseRequest;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  @NotBlank private String usernameOrEmail;

  @NotBlank private String password;

}
