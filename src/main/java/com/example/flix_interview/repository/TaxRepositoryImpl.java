package com.example.flix_interview.repository;

import com.example.flix_interview.tax.Country;
import com.example.flix_interview.tax.TaxPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TaxRepositoryImpl implements TaxRepository {

    private Map<Country, List<TaxPolicy>> taxPolicies = new HashMap<>();

    @Override
    public void saveNewTaxPolicy(Country country, TaxPolicy taxPolicy) {
        if (taxPolicies.containsKey(country)) {
            taxPolicies.get(country).add(taxPolicy);
        } else {
            taxPolicies.put(country, new ArrayList<>(List.of(taxPolicy)));
        }
    }

    @Override
    public List<TaxPolicy> getTaxPoliciesForCountry(Country country) {
        return taxPolicies.get(country);
    }

    @PostConstruct
    private void init() {
        taxPolicies.put(Country.GERMANY, new ArrayList<>(Arrays.asList(new TaxPolicy(0.0, 100.0, 7.0, LocalDate.of(2029, 7, 1)),
                new TaxPolicy(100.0, Double.MAX_VALUE, 19.0, LocalDate.of(2029, 7, 1)))));
        taxPolicies.put(Country.AUSTRIA, new ArrayList<>(Arrays.asList(new TaxPolicy(0.0, 100.0, 9.0, LocalDate.of(2029, 7, 1)),
                new TaxPolicy(100.0, Double.MAX_VALUE, 10.0, LocalDate.of(2029, 7, 1)))));
    }

}
