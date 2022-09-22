package com.example.flix_interview.service.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DistanceDto {

    private String country;
    private Double distance;
}
