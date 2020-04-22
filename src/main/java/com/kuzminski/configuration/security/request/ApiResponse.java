package com.kuzminski.configuration.security.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Boolean success;

    private String message;

}
