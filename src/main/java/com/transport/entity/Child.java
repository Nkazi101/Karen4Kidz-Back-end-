package com.transport.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "child")
@Data
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(name = "firstname")
    private String firstName; 

    @Column(name = "lastname")
    private String lastName; 

    @Column(name = "school")
    private String school; 

    @Column(name = "grade")
    private Double grade; 

    @Column(name = "phone")
    private String  phone; 

    @Column(name = "photo")
    private String photo; 

    @Column(name = "comments")
    private String comments;

    @ManyToOne(cascade=CascadeType.ALL)
    private User user;
    
}
