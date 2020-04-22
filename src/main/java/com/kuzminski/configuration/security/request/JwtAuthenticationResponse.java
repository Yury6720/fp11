package com.kuzminski.configuration.security.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;

}
