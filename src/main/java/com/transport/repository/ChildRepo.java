package com.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.entity.Child;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long>{
    
}
