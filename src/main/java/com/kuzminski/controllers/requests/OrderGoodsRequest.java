package com.kuzminski.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderGoodsRequest {
    @NotEmpty
    @NotNull
    private Long orderId;

    @NotEmpty
    @NotNull
    private Long goodsId;

    @NotEmpty
    @NotNull
    private Integer count;
}
