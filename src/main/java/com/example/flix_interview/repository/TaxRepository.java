package com.example.flix_interview.repository;

import com.example.flix_interview.tax.Country;
import com.example.flix_interview.tax.TaxPolicy;

import java.util.List;

public interface TaxRepository {

    void saveNewTaxPolicy(Country country, TaxPolicy taxPolicy);

    List<TaxPolicy> getTaxPoliciesForCountry(Country country);
}
