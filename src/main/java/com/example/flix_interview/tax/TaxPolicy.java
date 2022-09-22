package com.example.flix_interview.tax;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TaxPolicy {
    private Double distanceFromInclusive;
    private Double distanceToExclusive;
    private Double taxPercentage;
    private LocalDate dateWhenApplied;
}
