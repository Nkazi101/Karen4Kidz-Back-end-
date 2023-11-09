package com.transport.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.entity.Job;
import com.transport.entity.User;
import com.transport.enums.JobStatus;
import com.transport.repository.JobRepo;
import com.transport.service.JobService;
import com.transport.service.UserService;

@RestController
@CrossOrigin("*")
public class JobController {
    
    @Autowired
    UserService userService;

    @Autowired
    JobService jobService;

    @Autowired
    JobRepo jobRepo;

    @RequestMapping(
        value = "/createjob/{user_id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        method = RequestMethod.POST)
    public ResponseEntity<Object> createJob(@RequestBody Job job, @PathVariable("user_id") Long userId){
        try{
            System.out.println("here 1");
            User user = userService.findById(userId);            
            user.getCreatedJobs().add(job);
            jobService.save(job);
            userService.save(user);
            return new ResponseEntity<Object>("Your request has been submitted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

       @RequestMapping(
        value = "/selectjob/{user_id}/{job_id}", 
        // consumes = MediaType.APPLICATION_JSON_VALUE, 
        // produces = MediaType.APPLICATION_JSON_VALUE, 
        method = RequestMethod.PUT)
    public ResponseEntity<Object> createJob(@PathVariable("job_id") Long jobId, @PathVariable("user_id") Long userId){
        try{
            Job job = jobService.selectAvailablejob(userId, jobId);
            return new ResponseEntity<Object>(job, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @RequestMapping(
        value = "/availablejob", 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        method = RequestMethod.GET)
    public ResponseEntity<Object> availableJob(){
        try{
            List<Job> availableJobs = jobRepo.findByAssignedUserNull();
            return new ResponseEntity<Object>(availableJobs, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("findJobById/{id}")
    public ResponseEntity<Object> getPickupJobById(@PathVariable Long id) {
        Optional<Job> job = jobService.findJobById(id);
        return new ResponseEntity<Object>(job, HttpStatus.OK);
    }

  @PutMapping("/startjob/{id}")
public ResponseEntity<?> startPickupJob(@PathVariable Long id) {
    return jobService.findJobById(id).map(job -> {
        if (job.getStatus() == JobStatus.PENDING) {

            job.setStatus(JobStatus.IN_PROGRESS);
            job.setPickUpTime(new Date());
            Job updatedJob = jobService.saveJob(job);
            return ResponseEntity.ok(updatedJob);
        } else {
           
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Job is not in PENDING status");
        }
    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found"));
}

public static class LocationUpdateRequest {
    private Double latitude;
    private Double longitude;
    // Other fields related to the location update
    
    // Getters and setters
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}


@GetMapping("/jobstatus/{userId}/{status}")
    public ResponseEntity<List<Job>> getJobsByStatusAndUser(
            @PathVariable Long userId,
            @PathVariable JobStatus status) {
        
        List<Job> jobs = jobRepo.findByStatusAndAssignedUserId(status, userId);
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/{jobId}/complete")
    public ResponseEntity<Job> completeJob(@PathVariable Long jobId) {
        return jobRepo.findById(jobId)
            .map(job -> {
                job.setStatus(JobStatus.COMPLETED);
                job.setActualdropOffTime(new Date()); // Assuming you want to set this
                Job updatedJob = jobRepo.save(job);
                return ResponseEntity.ok(updatedJob);
            })
            .orElse(ResponseEntity.notFound().build());
    }


}
