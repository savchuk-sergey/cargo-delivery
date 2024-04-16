package ru.liga.Truck.service;

import lombok.extern.log4j.Log4j2;
import ru.liga.Cargo.model.Cargo;
import ru.liga.Truck.model.Truck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Log4j2
public class TruckReader {
    public List<Truck> readFromJsonFile(String filePath) {
        log.trace("readFromJsonFile - filePath: " + filePath);

        List<List<Integer>> truckPlacements = new ArrayList<>();
        List<Truck> trucks = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                log.trace("readFromJsonFile - file line: " + line);

//                if (!line.isEmpty()) {
//                    truckPlacements.add(getTruckPlacementsFromString(line));
//                }
//                if (line.isEmpty() || !scanner.hasNext()) {
//                    trucks.add(getCargoFromList(truckPlacements));
//                    truckPlacements = new ArrayList<>();
//                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файл не найден: " + e.getMessage());
            log.trace(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n")));
        }

        return null;
    }

//    private Truck getTruckFromString(String truckString) {
//        String[] fullnessLines = truckString.split("\\|");
//        List<List<Integer>> truckChars = Arrays.stream(fullnessLines)
//                .map(fullnessLine -> Arrays.stream(fullnessLine.chars().toArray())
//                        .map(fullnessChar -> Character.get (char)fullnessChar)
//                )
//                .collect(Collectors.toList());
//    }


//    public Truck getTruckFromString(String truckString, int height, int width) {
//        String[] fullnessLines = truckString.split("\\|");
//        List<List<Integer>> truck = Arrays.stream(fullnessLines)
//                .map(fullnessLine -> fullnessLine.chars()
//                        .mapToObj(Character::getNumericValue)
//                        .collect(Collectors.toList())).toList();
//
////        for(List<Integer> fullnessLine : truck) {
////            for(int i = width - 1; i <= 0; i--) {
////                f
////            }
////        }
//        return new Truck(truck, height, width);
//    }
}
