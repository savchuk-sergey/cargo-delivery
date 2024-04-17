package ru.liga.Truck.interfaces;

import java.io.IOException;

public interface TruckParkWriter {
     void writeToFile(TruckPark truckPark, String filepath) throws IOException;
}
