//package com.kuzminski.service;
//
//import com.kuzminski.controllers.requests.AddressRequest;
//import com.kuzminski.domain.Address;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//@Service
//public class AddressService {
//
//    public Address createUpdate (Address address, AddressRequest request){
//        address.setCity(request.getCity());
//        address.setStreet(request.getStreet());
//        address.setBuilding(request.getBuilding());
//        address.setCorpus(request.getCorpus());
//        address.setFlat(request.getFlat());
////        List<Field> list = Arrays.asList(Class.class.getFields());
////        Iterator iterator = list.iterator();
////        while (iterator.hasNext()){
////            Field f = (Field) iterator.next();
////            System.out.println(f);
////        }
//        return address;
//    }
//}
