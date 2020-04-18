package com.kuzminski.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressRequest {
    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String city;

    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String street;

    @NotEmpty
    @NotNull
    @Size(max = 20)
    private Integer building;

    @Size(max = 20)
    private Integer corpus;

    @Size(max = 20)
    private Integer flat;
}
