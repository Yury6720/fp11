package com.kuzminski.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PhoneRequest {
    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String username;

    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String phone;
}
