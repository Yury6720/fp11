package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.GenericIdRequest;
import com.kuzminski.controllers.requests.GoodsRequest;
import com.kuzminski.domain.Goods;
import com.kuzminski.domain.User;
import com.kuzminski.repository.GoodsRepository;
import com.kuzminski.service.GoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired private GoodsRepository goodsRepository;
    @Autowired private GoodsService goodsService;

    @ApiOperation(value = "Get User by Username ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/all")
    private ResponseEntity<List<Goods>> getAllGoods() {
        return new ResponseEntity<>(goodsRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Goods by Id ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping
    private ResponseEntity<Goods> getGoodsById(@ModelAttribute GenericIdRequest request) {
        return new ResponseEntity(goodsRepository.findById(request.getId()), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Get User by Username ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "{/goodsName}")
    private ResponseEntity<User> getUserByUsername(
            @Valid @ModelAttribute("goodsName") String goodsName) {
        return new ResponseEntity(goodsRepository.findGoodsByName(goodsName), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Add new Goods ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @PostMapping
    private ResponseEntity<Goods> addNewGoods(
            @ModelAttribute GoodsRequest goodsRequest) {
        Goods goods = new Goods();
        goodsRepository.saveAndFlush(goodsService.createUpdate(goods, goodsRequest));
        return new ResponseEntity(goods, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Goods by Id ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @PutMapping
    private ResponseEntity<Goods> updateGoods(
            @Valid @ModelAttribute GoodsRequest goodsRequest) {
        Goods goods = (goodsRepository.findById(goodsRequest.getId())).get();
        goodsRepository.saveAndFlush(goodsService.createUpdate(goods, goodsRequest));
        return new ResponseEntity(goods, HttpStatus.CREATED);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value = "Delete Goods")
    @DeleteMapping
    @ResponseStatus(HttpStatus.GONE)
    private void deleteUser(@ModelAttribute GenericIdRequest request) {
        goodsRepository.deleteById(request.getId());
    }

}
