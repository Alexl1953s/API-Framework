package practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperPractice {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{ \"name\":\"Mercedes\", \"year\": 2021 }";

        ObjectMapper mapper = new ObjectMapper();
        Car car1 = mapper.readValue(json, Car.class);

        System.out.println(car1.getName());
        System.out.println(car1.getYear());


        Car car2 = new Car();
        car2.setName("BMW");
        car2.setYear(2023);

       String car2AsJson =  mapper.writeValueAsString(car2);
       System.out.println(car2AsJson);
    }
}
