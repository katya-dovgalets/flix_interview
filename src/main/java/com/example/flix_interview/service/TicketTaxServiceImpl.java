package com.example.flix_interview.service;

import com.example.flix_interview.repository.TaxRepository;
import com.example.flix_interview.service.feign.DistanceDto;
import com.example.flix_interview.service.feign.DistanceFeignClient;
import com.example.flix_interview.tax.Country;
import com.example.flix_interview.tax.TaxPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TicketTaxServiceImpl implements TicketTaxService {
    private final DistanceFeignClient distanceFeignClient;
    private final TaxRepository taxRepository;

    @Override
    public Double calculateTax(String cityFrom, String cityTo, String date, String ticketPrice) {
        List<DistanceDto> distances = distanceFeignClient.getDistance(cityFrom, cityTo);

        var price = Double.valueOf(ticketPrice);
        return distances.stream()
                .mapToDouble(dto -> getTaxPercentage(Country.valueOf(dto.getCountry().toUpperCase(Locale.ROOT)),
                        dto.getDistance(), LocalDate.parse(date)))
                .map(percentage -> price * percentage / 100)
                .sum();
    }

    @Override
    public void addNewTaxPolicy(Country country, TaxPolicy taxPolicy) {
        taxRepository.saveNewTaxPolicy(country, taxPolicy);
    }

    private Double getTaxPercentage(Country country, Double distance, LocalDate date) {
        return taxRepository.getTaxPoliciesForCountry(country).stream()
                .filter(mod -> distance > mod.getDistanceFromInclusive() && distance < mod.getDistanceToExclusive())
                .filter(mod -> date.isEqual(mod.getDateWhenApplied()) || date.isAfter(mod.getDateWhenApplied()))
                .findFirst()
                .orElseThrow()
                .getTaxPercentage();
    }
}
