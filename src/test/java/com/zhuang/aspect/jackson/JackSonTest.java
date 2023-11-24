package com.zhuang.aspect.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuang.aspect.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JackSonTest {

    @Test
    void JsonToBean() {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";


        try {
            // JSON字符串-->Java对象
            Car car = objectMapper.readValue(carJson, Car.class);
            // JSON数组字符串-->Java对象数组
            Car[] cars2 = objectMapper.readValue(jsonArray, Car[].class);
            // JSON数组字符串-->List
            List<Car> carList = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>(){});
            System.out.println("car = " + car);
            System.out.println("cars2 = " + Arrays.toString(cars2));
            System.out.println("carList = " + carList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}