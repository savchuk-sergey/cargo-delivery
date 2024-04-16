package ru.liga.Truck.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;
import ru.liga.Truck.interfaces.TruckPark;
import ru.liga.Truck.model.Truck;
import ru.liga.Truck.util.TruckSerializer;

import java.io.File;
import java.io.IOException;

@Component
public class TruckParkWriter {
    public void writeToJson(TruckPark truckPark, String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Truck.class, new TruckSerializer());
        mapper.registerModule(module);
        mapper.writeValue(new File(filepath), truckPark);
    }
}
