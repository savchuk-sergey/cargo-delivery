package org.example.interfaces;

import org.example.model.Cargo;
import org.example.model.Truck;

public interface TruckLoader {
    void placeCargo(Truck truck, Cargo cargo);
}
