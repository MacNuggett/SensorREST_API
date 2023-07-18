package com.firstRestApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstRestApp.dto.MeasurementDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/measurements/add";

        for(int i=0; i<1000; i++) {
            Map<String, Object> jsonToSend = new HashMap<>();
            jsonToSend.put("value", -49.9 + Math.random()*99);
            int checkBool = (int) (Math.random() * 2);
            Boolean isRain;
            if(checkBool==0)
                isRain = Boolean.TRUE;
            else isRain = Boolean.FALSE;
            jsonToSend.put("isRaining", isRain);
            HashMap<String, String> sensorMap = new HashMap<>(1);
            sensorMap.put("name", "Test_sensor");
            jsonToSend.put("sensor", sensorMap);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend);

            String otvet = restTemplate.postForObject(url, request, String.class);

            System.out.println(otvet);
        }
    }
}
