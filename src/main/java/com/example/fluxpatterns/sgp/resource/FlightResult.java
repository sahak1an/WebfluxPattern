package com.example.fluxpatterns.sgp.resource;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FlightResult {
    private String airLine;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;
}
