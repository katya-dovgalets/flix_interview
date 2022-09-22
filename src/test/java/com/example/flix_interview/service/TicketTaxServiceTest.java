package com.example.flix_interview.service;


import com.example.flix_interview.repository.TaxRepository;
import com.example.flix_interview.service.feign.DistanceDto;
import com.example.flix_interview.service.feign.DistanceFeignClient;
import com.example.flix_interview.tax.TaxPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.flix_interview.tax.Country.AUSTRIA;
import static com.example.flix_interview.tax.Country.GERMANY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketTaxServiceTest {

    @Mock
    private DistanceFeignClient distanceFeignClient;
    @Mock
    private TaxRepository taxRepository;

    private TicketTaxService ticketTaxService;

    @BeforeEach
    void init() {
        ticketTaxService = new TicketTaxServiceImpl(distanceFeignClient, taxRepository);
    }

    @Test
    void shouldCalculateTaxAmount() {
        var cityFrom = "Berlin";
        var cityTo = "Vienna";
        var initialTicketPrice = "15";

        when(distanceFeignClient.getDistance(cityFrom, cityTo)).thenReturn(List.of(new DistanceDto(GERMANY.name(), 90.0),
                new DistanceDto(AUSTRIA.name(), 250.0)));
        when(taxRepository.getTaxPoliciesForCountry(GERMANY)).thenReturn(Arrays.asList(new TaxPolicy(0.0, 100.0, 7.0, LocalDate.of(2029, 7, 1)),
                new TaxPolicy(100.0, Double.MAX_VALUE, 19.0, LocalDate.of(2029, 7, 1))));
        when(taxRepository.getTaxPoliciesForCountry(AUSTRIA)).thenReturn(Arrays.asList(new TaxPolicy(0.0, 100.0, 9.0, LocalDate.of(2029, 7, 1)),
                new TaxPolicy(100.0, Double.MAX_VALUE, 10.0, LocalDate.of(2029, 7, 1))));

        Double actualTotalTaxAmount = ticketTaxService.calculateTax(cityFrom, cityTo, LocalDate.of(2029, 7, 1).toString(), initialTicketPrice);

        assertEquals(2.55, actualTotalTaxAmount);

    }
}
