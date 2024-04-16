package ru.liga.Truck.service;


import org.junit.Test;
import ru.liga.Truck.model.Truck;

public class TruckReaderTest {

    @Test
    public void getTruckFromString() {
        TruckReader truckReader = new TruckReader();

        Truck truck = truckReader.getTruckFromString("|33322|55555|99911|999666|999666", 6, 6);
        System.out.println(truck);
        truck.print();
        System.out.println(truck.getCargoList());
    }
}