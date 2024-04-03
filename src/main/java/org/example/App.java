package org.example;

import org.example.interfaces.CargoStore;
import org.example.interfaces.TruckLoader;
import org.example.interfaces.TruckPark;
import org.example.model.Cargo;
import org.example.model.Truck;
import org.example.service.BaseCargoStore;
import org.example.service.BaseTruckLoader;
import org.example.service.BaseTruckPark;
import org.example.service.CargoReader;

public class App {
    private static final int TRUCK_HEIGHT = 6;
    private static final int TRUCK_WIDTH = 6;
    private static final int FILEPATH_ARGUMENT_POSITION = 0;

    public static void main(String[] args) {
        String filepath = args[FILEPATH_ARGUMENT_POSITION];
        TruckLoader truckLoader = new BaseTruckLoader();
        CargoStore cargoStore = new BaseCargoStore();
        TruckPark truckPark = new BaseTruckPark();

        CargoReader.readCargoFromFile(cargoStore, filepath);

        for (Cargo cargo : cargoStore.getCargoes()) {
            Truck truck = new Truck(TRUCK_HEIGHT, TRUCK_WIDTH);
            truckLoader.placeCargo(truck, cargo);
            truckPark.addTruck(truck);
        }

        truckPark.printAllTrucks();
    }
}
