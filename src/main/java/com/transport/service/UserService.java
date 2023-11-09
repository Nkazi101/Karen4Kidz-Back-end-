package com.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.entity.Job;
import com.transport.entity.User;
import com.transport.enums.JobStatus;
import com.transport.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo; 

    public User save(User user) throws Exception {
        return userRepo.save(user);
    }

    public User signIn(User user) throws Exception {
        User foundUser = userRepo.findByEmail(user.getEmail());

        if (foundUser == null) {
            throw new Exception("User not found");
        }

        if(!foundUser.getPassword().equals(user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return foundUser;
    }
    

    public User findById(Long userId) throws Exception {

        if (userRepo.findById(userId).isPresent()) {
        
        return userRepo.findById(userId).get();
        
        } else {
        
        throw new Exception("no user found with that id");
        
        }

     }

}
