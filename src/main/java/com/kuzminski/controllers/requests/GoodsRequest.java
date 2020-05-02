package com.kuzminski.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GoodsRequest {

    private Long id;

    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String name;


    @NotNull
    private Double price;

    @NotNull
    private Double size;

    private String color;

}
