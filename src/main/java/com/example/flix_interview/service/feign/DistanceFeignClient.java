package com.example.flix_interview.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface DistanceFeignClient {

    @GetMapping(value = "/{cityFrom}/{cityTo}")
    List<DistanceDto> getDistance(@PathVariable String cityFrom, @PathVariable String cityTo);
}

