package com.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transport.entity.User;
import com.transport.service.UserService;


@RestController
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
        value = "/signIn", 
        method = RequestMethod.POST)
        @ResponseBody
    public ResponseEntity<Object> signIn(@RequestBody User user){
        try{
            User LoggedInUser = userService.signIn(user);
            return new ResponseEntity<Object>(LoggedInUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }




    



        @RequestMapping(
        value = "/signup", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(@RequestBody User user){
        try{
            User savedUser = userService.save(user);
            return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
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
    
       @RequestMapping(
        value = "/findUserById/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    public ResponseEntity<Object> findUserById(@PathVariable Long id) {
        try {
            User foundUser = userService.findById(id);
            return new ResponseEntity<Object>(foundUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
