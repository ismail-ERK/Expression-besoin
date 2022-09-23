package com.expressionbesoins.restexpbesoin.controller.api;

import com.expressionbesoins.restexpbesoin.dto.ApiDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/data")
public class ApiController {
    @GetMapping
    public ResponseEntity<ApiDto> getData(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ApiDto> entity = new HttpEntity<ApiDto>(headers);
        ApiDto apiDto = restTemplate.exchange("http://localhost:8089/api", HttpMethod.GET, entity, ApiDto.class).getBody();
        return new ResponseEntity<>(apiDto, new HttpHeaders(), HttpStatus.OK);
    }
}
