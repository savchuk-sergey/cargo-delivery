package ru.liga.Truck.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.Cargo.model.Cargo;
import ru.liga.Truck.exception.TruckNumberExceededException;
import ru.liga.Truck.interfaces.TruckLoader;
import ru.liga.Truck.model.Truck;
import ru.liga.command.CargoProcessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация интерфейса TruckLoader, представляющая загрузчик грузов на грузовик с помощью сортировки.
 */
@Component
@Log4j2
public class SortTruckLoader implements TruckLoader {
    /**
     * Размещает груз в грузовиках.
     *
     * @param cargos груз, который размещается на грузовике
     */
    @Override
    public List<Truck> placeCargos(List<Cargo> cargos, int height, int width, int maxTruckNumber) throws TruckNumberExceededException {
        log.trace("placeCargo - cargo: {}", cargos);
        List<Truck> trucks = new ArrayList<>();
        List<Cargo> sortedCargos = cargos.stream()
                .sorted(Comparator.comparing(Cargo::getWidth))
                .toList();
        Truck truck = new Truck(height, width);

        for (Cargo cargo : sortedCargos) {
            if (maxTruckNumber > 0 && trucks.size() > maxTruckNumber) {
                throw new TruckNumberExceededException(maxTruckNumber, trucks.size());
            }

            int truckFreeHeight = truck.getFreeHeight();
            int cargoVolume = cargo.getVolume();
            List<List<Integer>> size = cargo.getSize();

            if (truckFreeHeight < cargo.getHeight()) {
                trucks.add(truck);
                truck = new Truck(CargoProcessor.TRUCK_HEIGHT, CargoProcessor.TRUCK_WIDTH);
            }

            for (int i = 0; i < size.size() - 1; i++) {
                for (int j = 0; j < size.get(i).size(); j++) {
                    truck.occupyPlace(i + truckFreeHeight, j, cargoVolume);
                }
            }
        }

        trucks.add(truck);

        return trucks;
    }
}
