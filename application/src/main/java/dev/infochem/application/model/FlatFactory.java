package dev.infochem.application.model;

import dev.infochem.transactionapi.DataManager;

import java.time.LocalDate;
import java.util.ArrayDeque;

public class FlatFactory {
    private final DataManager<ArrayDeque<Flat>> dataManager;
    private Flat flat;
    public FlatFactory(DataManager<ArrayDeque<Flat>> dataManager) {
        this.dataManager = dataManager;
    }
    public FlatFactory create(String name, Coordinates coordinates, float area, Furnish furnish,
                       long numberOfBathrooms, float timeToMetroByTransport) {
        Flat flat = new Flat();
        flat.setId(dataManager.generateID());
        flat.setCreationDate(LocalDate.now());
        flat.setName(name);
        flat.setCoordinates(coordinates);
        flat.setArea(area);
        flat.setFurnish(furnish);
        flat.setNumberOfBathrooms(numberOfBathrooms);
        flat.setTimeToMetroByTransport(timeToMetroByTransport);
        this.flat = flat;
        return this;
    }

    public FlatFactory create(Flat flat) {
        this.flat = flat;
        flat.setId(dataManager.generateID());
        flat.setCreationDate(LocalDate.now());
        return this;
    }

    public FlatFactory addHouse(House house) {
        flat.setHouse(house);
        return this;
    }

    public FlatFactory addNumberOfRooms(Long numberOfRooms) {
        flat.setNumberOfRooms(numberOfRooms);
        return this;
    }

    public Flat build() {
        Flat flatBuild = flat;
        flat = null;
        return flatBuild;
    }
}
