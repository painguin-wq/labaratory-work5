package dev.infochem.application.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Flat.
 */
public class Flat {
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private float area;
    private Long numberOfRooms;
    private long numberOfBathrooms;
    private float timeToMetroByTransport;
    private Furnish furnish;
    private House house;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Field id should be more than 0");
        }
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Field name should be not null and not empty");
        }
        this.name = name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Field coordinates should be not null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Field creationDate should be not null");
        }
        this.creationDate = creationDate;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(float area) {
        if (area < 0) {
            throw new IllegalArgumentException("Field area should be more than 0");
        }
        this.area = area;
    }

    /**
     * Gets number of rooms.
     *
     * @return the number of rooms
     */
    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets number of rooms.
     *
     * @param numberOfRooms the number of rooms
     */
    public void setNumberOfRooms(Long numberOfRooms) {
        if (numberOfRooms != null && numberOfRooms < 0) {
            throw new IllegalArgumentException("Field numberOfRooms should be more than 0");
        }
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Gets number of bathrooms.
     *
     * @return the number of bathrooms
     */
    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * Sets number of bathrooms.
     *
     * @param numberOfBathrooms the number of bathrooms
     */
    public void setNumberOfBathrooms(long numberOfBathrooms) {
        if (numberOfBathrooms < 0) {
            throw new IllegalArgumentException("Field numberOfBathrooms should be more than 0");
        }
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * Gets time to metro by transport.
     *
     * @return the time to metro by transport
     */
    public float getTimeToMetroByTransport() {
        return timeToMetroByTransport;
    }

    /**
     * Sets time to metro by transport.
     *
     * @param timeToMetroByTransport the time to metro by transport
     */
    public void setTimeToMetroByTransport(float timeToMetroByTransport) {
        if (timeToMetroByTransport < 0) {
            throw new IllegalArgumentException("Field timeToMetroByTransport should be more than 0");
        }
        this.timeToMetroByTransport = timeToMetroByTransport;
    }

    /**
     * Gets furnish.
     *
     * @return the furnish
     */
    public Furnish getFurnish() {
        return furnish;
    }

    /**
     * Sets furnish.
     *
     * @param furnish the furnish
     */
    public void setFurnish(Furnish furnish) {
        if (furnish == null) {
            throw new IllegalArgumentException("Field furnish should be not null");
        }
        this.furnish = furnish;
    }

    /**
     * Gets house.
     *
     * @return the house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Sets house.
     *
     * @param house the house
     */
    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", numberOfBathrooms=" + numberOfBathrooms +
                ", timeToMetroByTransport=" + timeToMetroByTransport +
                ", furnish=" + furnish +
                ", house=" + house +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return id == flat.id && Float.compare(area, flat.area) == 0 && numberOfBathrooms == flat.numberOfBathrooms
                && Float.compare(timeToMetroByTransport, flat.timeToMetroByTransport) == 0 && Objects.equals(name, flat.name)
                && Objects.equals(coordinates, flat.coordinates) && Objects.equals(creationDate, flat.creationDate)
                && Objects.equals(numberOfRooms, flat.numberOfRooms) && furnish == flat.furnish && Objects.equals(house, flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area,
                numberOfRooms, numberOfBathrooms, timeToMetroByTransport, furnish, house);
    }
}
