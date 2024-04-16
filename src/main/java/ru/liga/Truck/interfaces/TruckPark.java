package ru.liga.Truck.interfaces;

import ru.liga.Truck.model.Truck;

import java.util.List;

public interface TruckPark {
    void add(Truck truck);
    void addAll(List<Truck> truck);
    List<Truck> getAll();
    void print();

    List<String> toListString();

    void setTrucks(List<Truck> trucks);
}
