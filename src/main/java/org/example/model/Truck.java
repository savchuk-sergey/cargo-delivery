package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Truck {
    private final List<List<Integer>> placements;

    public Truck(int height, int width) {
        if (height <= 0 || width <= 0) {
            String errorMessage = "";
            if (height <= 0) {
                errorMessage += "Неверное значение высоты: " + height + ". Высота должна быть больше нуля.";
            }
            if (width <= 0) {
                errorMessage += "Неверное значение ширины: " + width + ". Ширина должна быть больше нуля.";
            }
            throw new IllegalArgumentException(errorMessage);
        }

        this.placements = new ArrayList<>(height);

        for (int i = 0; i < height ; i++) {
            this.placements.add(new ArrayList<>(width));
            for(int j = 0; j < width; j++) {
                this.placements.get(i).add(null);
            }
        }
    }

    public int getFreeVolume() {
        return placements.stream()
                .flatMap(List::stream)
                .filter(Objects::isNull)
                .toList()
                .size();
    }

    public void print() {

        for (int i = placements.size() - 1; i >= 0; i--) {
            System.out.print("+");
            String line = placements.get(i).stream()
                    .map(p -> p != null ? p.toString() : " ")
                    .collect(Collectors.joining());
            System.out.println(line + "+");
        }

        System.out.println("+" + "+".repeat(placements.get(0).size()) + "+\n");
    }

    public void occupyPlace(int vertical, int horizontal, int cargoVolume) {
        placements.get(vertical).set(horizontal, cargoVolume);
    }
}
