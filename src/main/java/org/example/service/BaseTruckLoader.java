package org.example.service;

import org.example.interfaces.TruckLoader;
import org.example.model.Cargo;
import org.example.model.Truck;

import java.util.List;

public class BaseTruckLoader implements TruckLoader {
    @Override
    public void placeCargo(Truck truck, Cargo cargo) {
        int cargoVolume = cargo.getVolume();
        List<List<Integer>> size = cargo.getSize();

        for (int i = 0; i < size.size(); i++) {
            for (int j = 0; j < size.get(i).size(); j++) {
                truck.occupyPlace(i, j, cargoVolume);
            }
        }
    }
}
