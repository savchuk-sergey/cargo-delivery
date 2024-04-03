package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cargo {
    private final int volume;
    private final List<List<Integer>> size;

    public Cargo(int volume, List<List<Integer>> size) {
        this.volume = volume;
        this.size = size;
    }

    public List<List<Integer>> getSize() {
        return size;
    }

    public int getVolume() {
        return volume;
    }
}
