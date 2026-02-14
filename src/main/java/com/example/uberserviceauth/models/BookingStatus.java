package com.example.uberserviceauth.models;

import jakarta.persistence.Table;

@Table
public enum BookingStatus {

    //TODO [Reverse Engineering] generate columns from DB
    SCHEDULED,
    Canceled,
    CAR_ARRIVED,
    ASSIGNING_DRIVER,
    IN_RIDE,
    COMPLETED
}