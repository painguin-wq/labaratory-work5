package dev.infochem.application.model;

import dev.infochem.transactionapi.DataManager;

import java.time.LocalDate;
import java.util.ArrayDeque;

/**
 * The type Flat factory.
 */
public class FlatFactory {
    private final DataManager<ArrayDeque<Flat>> dataManager;
    private Flat flat;

    /**
     * Instantiates a new Flat factory.
     *
     * @param dataManager the data manager
     */
    public FlatFactory(DataManager<ArrayDeque<Flat>> dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * Create flat factory.
     *
     * @param name                   the name
     * @param coordinates            the coordinates
     * @param area                   the area
     * @param furnish                the furnish
     * @param numberOfBathrooms      the number of bathrooms
     * @param timeToMetroByTransport the time to metro by transport
     * @return the flat factory
     */
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

    /**
     * Create flat factory.
     *
     * @param flat the flat
     * @return the flat factory
     */
    public FlatFactory create(Flat flat) {
        this.flat = flat;
        flat.setId(dataManager.generateID());
        flat.setCreationDate(LocalDate.now());
        return this;
    }

    /**
     * Add house flat factory.
     *
     * @param house the house
     * @return the flat factory
     */
    public FlatFactory addHouse(House house) {
        flat.setHouse(house);
        return this;
    }

    /**
     * Add number of rooms flat factory.
     *
     * @param numberOfRooms the number of rooms
     * @return the flat factory
     */
    public FlatFactory addNumberOfRooms(Long numberOfRooms) {
        flat.setNumberOfRooms(numberOfRooms);
        return this;
    }

    /**
     * Build flat.
     *
     * @return the flat
     */
    public Flat build() {
        Flat flatBuild = flat;
        flat = null;
        return flatBuild;
    }
}
