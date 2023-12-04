package com.example.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dungpt
 * @created 2023/12/04 - 10:43 AM
 */

@RestController
public class ExternalController {

    private final RestTemplate restTemplate;

    public ExternalController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/lims/auth")
    public ResponseEntity<String> auth() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        requestData.add("x1", "a1");
        requestData.add("x2", "a2");
        requestData.add("x3", "a3");
        requestData.add("x4", "a4");
        requestData.add("x5", "a5");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestData, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "https://xxx.info/authenticate",
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
        } else {

            return new ResponseEntity<>(responseEntity.getStatusCode());
        }
    }
}
