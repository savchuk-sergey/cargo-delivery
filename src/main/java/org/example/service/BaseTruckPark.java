package org.example.service;

import org.example.interfaces.TruckPark;
import org.example.model.Truck;

import java.util.ArrayList;
import java.util.List;

public class BaseTruckPark implements TruckPark {
    private final List<Truck> trucks;

    public BaseTruckPark() {
        this.trucks = new ArrayList<>();
    }

    @Override
    public void addTruck(Truck truck) {
        trucks.add(truck);
    }

    @Override
    public void printAllTrucks() {
        for (Truck truck : trucks) {
            truck.print();
        }
    }
}
