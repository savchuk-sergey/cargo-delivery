package ru.liga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.Cargo.interfaces.CargoStore;
import ru.liga.Cargo.service.BaseCargoStore;
import ru.liga.Cargo.service.CargoReader;
import ru.liga.Truck.interfaces.TruckLoader;
import ru.liga.Truck.interfaces.TruckPark;
import ru.liga.Truck.service.BaseTruckLoader;
import ru.liga.Truck.service.BaseTruckPark;
import ru.liga.Truck.service.SortTruckLoader;

@Configuration
public class Config {
    @Bean
    public TruckLoader sortTruckLoader() {
        return new SortTruckLoader();
    }

    @Bean
    public TruckLoader simpleTruckLoader() {
        return new BaseTruckLoader();
    }
}
