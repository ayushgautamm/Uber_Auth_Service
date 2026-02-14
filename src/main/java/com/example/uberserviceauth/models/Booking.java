package com.example.uberserviceauth.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Booking extends BaseModel {

    @Enumerated(EnumType.STRING)
    private  BookingStatus bookingStatus;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;
    private Long Distance;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;



}
