package com.zhuang.aspect.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuang.aspect.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class JackSonTest {

    @Test
    void JsonToBean() {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        try {
            Car car = objectMapper.readValue(carJson, Car.class);

            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (
                IOException e) {
            e.printStackTrace();

        }

    }
}