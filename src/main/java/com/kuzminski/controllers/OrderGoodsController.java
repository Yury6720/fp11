//package com.kuzminski.controllers;
//
//import com.kuzminski.controllers.requests.GenericIdRequest;
//import com.kuzminski.controllers.requests.OrderAddressUpdateRequest;
//import com.kuzminski.controllers.requests.OrderGoodsRequest;
//import com.kuzminski.domain.Goods;
//import com.kuzminski.domain.OrderGoods;
//import com.kuzminski.repository.OrderGoodsRepository;
//import com.kuzminski.service.OrderService;
//import com.kuzminski.service.OrderValidator;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/orderGoods")
//public class OrderGoodsController {
//
//    @Autowired private OrderGoodsRepository orderGoodsRepository;
//    @Autowired private OrderValidator orderValidator;
//    @Autowired private OrderService orderService;
//
//    @ApiOperation(value = "Get all Goods by Order")
//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "Auth-Token",
//                    value = "token",
//                    required = true,
//                    dataType = "string",
//                    paramType = "header")
//    })
//    @GetMapping("/{orderId}")
//    private ResponseEntity getAllGoods(@Valid @ModelAttribute GenericIdRequest idRequest,
//                                       HttpServletRequest request) {
//        if (orderValidator.orderAccessor(request, idRequest.getId())){
//            return new ResponseEntity(orderGoodsRepository.findAllByOrderId(idRequest.getId()), HttpStatus.OK);
//        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
//
//    @ApiOperation(value = "Add Goods to Order")
//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "Auth-Token",
//                    value = "token",
//                    required = true,
//                    dataType = "string",
//                    paramType = "header")
//    })
//    @PostMapping
//    private ResponseEntity addGoods(
//            @ModelAttribute OrderGoodsRequest orderGoodsRequest,
//            HttpServletRequest request) {
//        if (orderValidator.orderAccessor(request, orderGoodsRequest.getOrderId())) {
//            OrderGoods orderGoods = orderGoodsRepository.saveAndFlush(orderService.addGoods(orderGoodsRequest));
//
//            return new ResponseEntity(orderGoods, HttpStatus.OK);
//        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
//
//}
