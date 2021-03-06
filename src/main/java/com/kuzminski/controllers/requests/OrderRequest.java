package com.kuzminski.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OrderRequest {

    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String username;

}
