package com.transport.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class LocationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Job pickupJob;

    private Double latitude;
    private Double longitude;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

}
