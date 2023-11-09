package com.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.entity.Child;
import com.transport.entity.User;
import com.transport.service.ChildService;

@RestController
public class ChildController {

    @Autowired
    private ChildService childservice;

    @RequestMapping(
        value = "/addChild/{id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        method = RequestMethod.POST)
    public ResponseEntity<Object> addChild(@PathVariable Long id, @RequestBody Child child){
        try{
            //User savedUser = userService.save(user);
            Child newChild = childservice.addChildtoUser(id, child);
            return new ResponseEntity<Object>(newChild, HttpStatus.CREATED);
            } catch (DataIntegrityViolationException e) {
            System.out.println("Duplicate email");
            return new ResponseEntity<Object>("Duplicate Email", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}