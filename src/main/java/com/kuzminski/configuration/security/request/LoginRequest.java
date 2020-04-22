package com.kuzminski.configuration.security.request;

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
