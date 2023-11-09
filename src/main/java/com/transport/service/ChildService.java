package com.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.entity.Child;
import com.transport.entity.User;
import com.transport.repository.ChildRepo;
import com.transport.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChildService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChildRepo childRepo;
    
    public Child addChildtoUser(Long id, Child child) {
        User user = userRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found."));
        child.setUser(user);
        return childRepo.save(child);
        
    }


    
}
