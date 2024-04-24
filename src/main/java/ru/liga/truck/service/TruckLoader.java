package ru.liga.truck.service;

import org.springframework.stereotype.Service;
import ru.liga.cargo.Cargo;
import ru.liga.truck.Truck;
import ru.liga.truck.exception.TruckNumberExceededException;

import java.util.List;

@Service
public interface TruckLoader {
    List<Truck> placeCargos(List<Cargo> cargos, int height, int width, int maxTruckNumber) throws TruckNumberExceededException;
}
