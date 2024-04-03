package org.example.service;

import org.example.interfaces.CargoStore;
import org.example.model.Cargo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CargoReader {
    public static void readCargoFromFile(CargoStore cargoStore, String filePath) {
        List<List<Integer>> cargoSizes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    List<Integer> sizes = Arrays.stream(line.split(""))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    cargoSizes.add(sizes);
                }
                if (line.isEmpty() || !scanner.hasNext()) {
                    handleCargoVolume(cargoStore, cargoSizes);
                    cargoSizes = new ArrayList<>();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    private static void handleCargoVolume(CargoStore cargoStore, List<List<Integer>> sizes) {
        int volume = sizes.get(0).get(0);

        Cargo cargo = new Cargo(volume, sizes);
        cargoStore.addCargo(cargo);
    }
}