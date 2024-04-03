package org.example;

import org.example.interfaces.TruckLoader;
import org.example.model.Cargo;
import org.example.model.Truck;
import org.example.service.BaseTruckLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BaseTruckLoaderTest {

    @Test
    void testPlaceCargo() {
        // Создать объект класса BaseTruckLoader
        TruckLoader truckLoader = new BaseTruckLoader();

        // Создать объект класса Truck
        Truck truck = new Truck(6, 6);

        // Создать объект класса Cargo
        Cargo cargo = new Cargo(9, List.of(
                List.of(9, 9, 9),
                List.of(9, 9, 9),
                List.of(9, 9, 9)
        ));

        // Разместить груз в грузовике
        truckLoader.placeCargo(truck, cargo);



        // Проверить, что груз был размещен правильно
        assertThat(truck.getFreeVolume()).isEqualTo(27);
    }
}