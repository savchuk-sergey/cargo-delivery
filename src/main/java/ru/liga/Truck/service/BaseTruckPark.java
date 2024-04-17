package ru.liga.Truck.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.Truck.interfaces.TruckPark;
import ru.liga.Truck.model.Truck;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class BaseTruckPark implements TruckPark {
    private final List<Truck> trucks;

    public BaseTruckPark() {
        log.trace("initialized");
        this.trucks = new ArrayList<>();
    }

    @Override
    public void add(Truck truck) {
        log.trace("add - truck: {}", truck);
        trucks.add(truck);
    }

    @Override
    public void print() {
        log.trace("print");
        for (Truck truck : trucks) {
            truck.print();
        }
    }

    @Override
    public List<Truck> getTrucks() {
        return trucks;
    }

    @Override
    public void addAll(List<Truck> trucks) {
        this.trucks.addAll(trucks);
    }
}
