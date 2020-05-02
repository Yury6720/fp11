package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.AddressRequest;
import com.kuzminski.controllers.requests.GenericIdRequest;
import com.kuzminski.domain.Address;
import com.kuzminski.repository.AddressRepository;
import com.kuzminski.service.AddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired private AddressRepository addressRepository;
  @Autowired private AddressService addressService;

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Get All Addresses")
  @GetMapping("/all")
  public ResponseEntity<List<Address>> getAddressList() {
    return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Add new Delivery Address")
  @PostMapping
  private ResponseEntity<Address> createNewAddress(@Valid @ModelAttribute AddressRequest request) {
    Address address = new Address();
    addressRepository.saveAndFlush(addressService.createUpdate(address, request));
    return new ResponseEntity<>(address, HttpStatus.CREATED);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Delete Delivery Address")
  @DeleteMapping
  @ResponseStatus(HttpStatus.GONE)
  private void deleteAddress(@ModelAttribute GenericIdRequest genericIdRequest) {
    addressRepository.deleteById(genericIdRequest.getId());
  }
}
