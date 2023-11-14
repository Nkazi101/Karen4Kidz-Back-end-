package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.transport.entity.Job;
import com.transport.enums.JobStatus;


@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    @Query(value = "select * from job where created_user_Id != ?1", nativeQuery = true )
    List<Job> getAvailable(Long id);

    List<Job> findByAssignedUserNull();

    List<Job> findByStatusAndAssignedUserId(JobStatus status, Long id);

    List<Job> findByStatus(JobStatus status);

    
}
