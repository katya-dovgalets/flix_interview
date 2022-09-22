package com.example.flix_interview.service;

import com.example.flix_interview.tax.Country;
import com.example.flix_interview.tax.TaxPolicy;

public interface TicketTaxService {

    Double calculateTax(String cityFrom, String cityTo, String date, String ticketPrice);

    void addNewTaxPolicy(Country country, TaxPolicy taxPolicy);
}
