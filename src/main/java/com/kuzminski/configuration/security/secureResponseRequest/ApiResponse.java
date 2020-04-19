package com.kuzminski.configuration.security.secureResponseRequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Boolean success;

    private String message;

}
