package dev.infochem.application.model;


import java.util.Objects;

public class House {
    private String name;
    private Integer year;
    private int numberOfLifts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if (year < 0) {
            throw new IllegalArgumentException("Field year should be more than 0");
        }
        this.year = year;
    }

    public int getNumberOfLifts() {
        return numberOfLifts;
    }

    public void setNumberOfLifts(int numberOfLifts) {
        if (numberOfLifts < 0) {
            throw new IllegalArgumentException("Field numberOfLifts should be more than 0");
        }
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", numberOfLifts=" + numberOfLifts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return numberOfLifts == house.numberOfLifts && Objects.equals(name, house.name) && Objects.equals(year, house.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, numberOfLifts);
    }
}
