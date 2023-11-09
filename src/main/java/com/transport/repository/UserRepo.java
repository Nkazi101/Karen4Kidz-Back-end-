package com.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

    public User findByEmail (String email);
    
}
