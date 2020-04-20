package com.kuzminski.controllers;

import com.kuzminski.domain.Goods;
import com.kuzminski.domain.User;
import com.kuzminski.repository.GoodsRepository;
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

    @ApiOperation(value = "Get User by Username ")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Auth-Token",
                    value = "token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @GetMapping("/all")
    private ResponseEntity<List<Goods>> getAllGoods() {
        return new ResponseEntity<>(goodsRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get User by Username ")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Auth-Token",
                    value = "token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @GetMapping(value = "/{id}")
    private ResponseEntity<Goods> getGoodsById(@PathVariable("id") Long goodsId) {
        return new ResponseEntity(goodsRepository.findById(goodsId), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Get User by Username ")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Auth-Token",
                    value = "token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @GetMapping(value = "{/goodsName}")
    private ResponseEntity<User> getUserByUsername(
            @Valid @ModelAttribute("goodsName") String goodsName) {
        return new ResponseEntity(goodsRepository.findGoodsByName(goodsName), HttpStatus.FOUND);
    }





    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Auth-Token",
                    value = "token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @ApiOperation(value = "Delete Goods")
    @DeleteMapping(value = "/{goodsId}")
    @ResponseStatus(HttpStatus.GONE)
    private void deleteUser(@PathVariable("id") Long goodsId) {
        goodsRepository.deleteById(goodsId);
    }
    //
}
