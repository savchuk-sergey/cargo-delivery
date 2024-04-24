package ru.liga.truck.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.cargo.Cargo;
import ru.liga.truck.Truck;
import ru.liga.truck.exception.TruckNumberExceededException;
import ru.liga.truck.service.TruckLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса TruckLoader, представляющая базовый загрузчик грузов на грузовик.
 */
@Component
@Log4j2
public class SimpleTruckLoader implements TruckLoader {
    /**
     * Размещает груз в грузовике.
     *
     * @param cargos груз, который размещается на грузовике
     */
    @Override
    public List<Truck> placeCargos(List<Cargo> cargos, int height, int width, int maxTruckNumber) throws TruckNumberExceededException {
        log.trace("placeCargo - cargo: {}", cargos);
        List<Truck> trucks = new ArrayList<>();

        for (Cargo cargo : cargos) {
            if (maxTruckNumber > 0 && trucks.size() > maxTruckNumber) {
                throw new TruckNumberExceededException(maxTruckNumber, trucks.size());
            }

            int cargoVolume = cargo.getVolume();
            List<List<Integer>> size = cargo.getSize();
            Truck truck = new Truck(height, width);

            for (int i = 0; i < size.size(); i++) {
                for (int j = 0; j < size.get(i).size(); j++) {
                    truck.occupyPlace(i, j, cargoVolume);
                }
            }

            trucks.add(truck);
        }

        return trucks;
    }
}
