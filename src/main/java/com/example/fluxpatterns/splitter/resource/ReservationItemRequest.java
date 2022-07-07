package com.example.fluxpatterns.splitter.resource;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class ReservationItemRequest {
    private ReservationType type;
    private String category;
    private String city;
    private LocalDate from;
    private LocalDate to;
}
