package com.kuzminski.controllers;

import com.kuzminski.domain.Address;
import com.kuzminski.repository.AddressRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    @ApiOperation(value = "Get all Customers from server")
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAddressList() {

        return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
    }

}
