package com.transport.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.controller.JobController;
import com.transport.entity.Job;
import com.transport.entity.LocationHistory;
import com.transport.entity.User;
import com.transport.enums.JobStatus;
import com.transport.repository.JobRepo;
import com.transport.repository.LocationHistoryRepo;
import com.transport.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LocationHistoryRepo locationHistoryRepository;

    @Transactional
    public boolean updateLocation(Long jobId, JobController.LocationUpdateRequest locationUpdateRequest) {
        Optional<Job> jobOptional = jobRepo.findById(jobId);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();

            // Create new LocationHistory
            LocationHistory newLocationHistory = new LocationHistory();
            newLocationHistory.setPickupJob(job);
            newLocationHistory.setLatitude(locationUpdateRequest.getLatitude());
            newLocationHistory.setLongitude(locationUpdateRequest.getLongitude());
            newLocationHistory.setTimestamp(new Date()); 

            // Save the new LocationHistory
            locationHistoryRepository.save(newLocationHistory);

            // Optionally, if you want to update the job with the latest location
            // job.setLastKnownLatitude(locationUpdateRequest.getLatitude());
            // job.setLastKnownLongitude(locationUpdateRequest.getLongitude());
            // pickupJobRepository.save(job);

            return true;
        } else {
            // Job ID does not exist or some other issue
            return false;
        }
    }

    public Job save(Job job) throws Exception {
        return jobRepo.save(job);
    }
    
    // public Job findById(Long jobId) throws Exception {

    //     if (jobRepo.findById(jobId).isPresent()) {
        
    //     return jobRepo.findById(jobId).get();
        
    //     } else {
        
    //     throw new Exception("no jobs found with that id");
        
    //     }

    //  }

     public Optional<Job> findJobById(Long id) {
        return jobRepo.findById(id);
    }

    //  public List<Job> getAllJobs(Long id) throws Exception{

    //     try {
    //        return jobRepo.getAvailable(id);
             
    //     } catch (Exception e){
    //         throw new Exception("no jobs available");
    //     }
       
    //  }

     
    //  public List<Job> getAllJobs(Long id) throws Exception{

    //     try {
    //        return jobRepo.getAvailable(id);
             
    //     } catch (Exception e){
    //         throw new Exception("no jobs available");
    //     }
       
    //  }

    public Job selectAvailablejob(Long userId, Long jobId){

        Job availablejob = jobRepo.findById(jobId).orElseThrow(()-> new EntityNotFoundException("Job not found"));

        User loggedinUser = userRepo.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found."));

        // loggedinUser.getAcceptedJobs().add(availablejob);

       availablejob.setAssignedUser(loggedinUser);
       availablejob.setStatus(JobStatus.PENDING);
        userRepo.save(loggedinUser);

        return jobRepo.save(availablejob);
    }

    public Job saveJob(Job job) {
        return jobRepo.save(job);
    }


}
