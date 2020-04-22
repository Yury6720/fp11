package com.kuzminski.service;

import com.kuzminski.controllers.requests.GoodsRequest;
import com.kuzminski.domain.Goods;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    public Goods createUpdate(Goods goods, GoodsRequest request) {
        goods.setName(request.getName());
        goods.setPrice(request.getPrice());
        goods.setSize(request.getSize());
        goods.setColor(request.getColor());
        return goods;
    }
}
