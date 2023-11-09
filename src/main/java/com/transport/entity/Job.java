package com.transport.entity;

import java.time.LocalDate;
import java.util.Date;

import com.transport.enums.JobStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;




@Entity
@Table(name="job")
@Data
public class Job {

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "child_Id", referencedColumnName = "id")
    private Child child;

    @ManyToOne
    private User assignedUser;

    // @Column(name= "status")
    // private Boolean status;

    // @Column(name= "availablejob")
    // private Boolean availableJob;

    // @Column(name= "accceptedjob")
    // private Boolean acceptedJob;    

    // @Column(name= "completedjob")
    // private Boolean completedJob;

    @Column(name = "pickupaddress")
    private String pickUpAddress;

    @Column(name= "pickuplongitude")
    private Double pickUpLongitude;

    @Column(name= "pickuplatitude")
    private Double pickUpLatitude;

    @Column(name = "dropoffaddress")
    private String dropoffAddress;
    
    @Column(name= "dropofflongitude")
    private Double dropOffLongitude;

    @Column(name= "dropofflatitude")
    private Double dropOffLatitude;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actualPickUpTime;

    @Temporal(TemporalType.TIMESTAMP)
     private Date actualdropOffTime;

    @Column(name= "pickuptime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickUpTime;
    
    @Column(name= "dropofftime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dropOffTime;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date estimatedDropoffDateTime;

    @Column(name= "pickupdate")
    private LocalDate pickUpDate;
    
    @Column(name= "dropoffDate")
    private LocalDate dropOffDate;

    @Enumerated(EnumType.STRING)
    private JobStatus status;
        

    
}
