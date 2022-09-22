package com.example.flix_interview.controller;

import com.example.flix_interview.service.TicketTaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxes")
@RequiredArgsConstructor
@Slf4j
public class TicketTaxController {

    private final TicketTaxService ticketTaxService;

    @GetMapping("/{cityFrom}/{cityTo}")
    public Double getTax(@PathVariable String cityFrom, @PathVariable String cityTo,
                         @RequestParam String date, @RequestParam String ticketPrice) {
        log.info("Got request to calculate tax for ticket from {} to {} for date {} with price {}", cityFrom, cityTo,
                date, ticketPrice);
        return ticketTaxService.calculateTax(cityFrom, cityTo, date, ticketPrice);
    }
}
