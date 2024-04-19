package dev.infochem.application.model;

import java.time.LocalDate;
import java.util.Objects;

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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Field id should be more than 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Field name should be not null and not empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Field coordinates should be not null");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Field creationDate should be not null");
        }
        this.creationDate = creationDate;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (area < 0) {
            throw new IllegalArgumentException("Field area should be more than 0");
        }
        this.area = area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        if (numberOfRooms != null && numberOfRooms < 0) {
            throw new IllegalArgumentException("Field numberOfRooms should be more than 0");
        }
        this.numberOfRooms = numberOfRooms;
    }

    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(long numberOfBathrooms) {
        if (numberOfBathrooms < 0) {
            throw new IllegalArgumentException("Field numberOfBathrooms should be more than 0");
        }
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public float getTimeToMetroByTransport() {
        return timeToMetroByTransport;
    }

    public void setTimeToMetroByTransport(float timeToMetroByTransport) {
        if (timeToMetroByTransport < 0) {
            throw new IllegalArgumentException("Field timeToMetroByTransport should be more than 0");
        }
        this.timeToMetroByTransport = timeToMetroByTransport;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        if (furnish == null) {
            throw new IllegalArgumentException("Field furnish should be not null");
        }
        this.furnish = furnish;
    }

    public House getHouse() {
        return house;
    }

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
