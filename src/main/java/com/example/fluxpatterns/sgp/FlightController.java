package com.example.fluxpatterns.sgp;

import com.example.fluxpatterns.sgp.resource.FlightResult;
import com.example.fluxpatterns.sgp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sgp")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/flight/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FlightResult> flights(@PathVariable("from") String from,@PathVariable("to") String to){
        return flightService.getFlights(from,to);
    }

}
