package com.transport.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "firstname")
    private String firstName; 

    @Column(name = "lastname")
    private String lastName; 

    @Column(name = "email", unique = true)
    private String email; 

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phone")
    private String phone;

    @Column(name = "vehicledescription")
    private String vehicleDescription;

    @Column(name = "vehiclelicense")
    private String vehicleLicense;

    // @OneToMany
    // @JoinColumn(name = "user_Id", referencedColumnName = "id")
    // private List<Child> userChildren;

    @OneToMany
    @JoinColumn(name = "created_user_Id", referencedColumnName = "id")
    private List<Job> createdJobs;

    // @OneToMany
    // @JoinColumn(name = "accepted_user_Id", referencedColumnName = "id")
    // private List<Job> acceptedJobs;

    
}
