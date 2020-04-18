package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.AddressRequest;
import com.kuzminski.domain.Address;
import com.kuzminski.repository.AddressRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;


    @ApiOperation(value = "Get all Customers from server")
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAddressList() {
        return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
    }

//    @ApiOperation(value = "Add new Delivery Address")
//    @PostMapping
//    private ResponseEntity <Address> createNewAddress (@RequestBody AddressRequest request){
//        Address address = new Address();
//        addressRepository.saveAndFlush(a)
//        return
//    }
}
