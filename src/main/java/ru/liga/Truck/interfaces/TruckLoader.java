package ru.liga.Truck.interfaces;

import org.springframework.stereotype.Service;
import ru.liga.Cargo.model.Cargo;
import ru.liga.Truck.exception.TruckNumberExceededException;
import ru.liga.Truck.model.Truck;

import java.util.List;

@Service
public interface TruckLoader {
    List<Truck> placeCargos(List<Cargo> cargos, int height, int width, int maxTruckNumber) throws TruckNumberExceededException;
}
