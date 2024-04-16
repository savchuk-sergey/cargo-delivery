package ru.liga.Cargo.model;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Cargo {
    private final int volume;
    private final List<List<Integer>> size;

    public Cargo(int volume, List<List<Integer>> size) {
        log.trace("initialization");
        log.debug("new Cargo(volume: {}, size: {})", volume, size);
        this.volume = volume;
        this.size = size;
    }

    public int getWidth() {
        return size.get(0).size();
    }

    public List<List<Integer>> getSize() {
        log.trace("getSize: {}", size);
        return size;
    }

    public int getVolume() {
        log.trace("getVolume: {}", volume);
        return volume;
    }

    public int getHeight() {
        return size.size();
    }

    @Override
    public String toString() {
        return size.toString();
    }
}
