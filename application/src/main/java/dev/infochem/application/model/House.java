package dev.infochem.application.model;


import java.util.Objects;

/**
 * The type House.
 */
public class House {
    private String name;
    private Integer year;
    private int numberOfLifts;

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
        this.name = name;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Integer year) {
        if (year < 0) {
            throw new IllegalArgumentException("Field year should be more than 0");
        }
        this.year = year;
    }

    /**
     * Gets number of lifts.
     *
     * @return the number of lifts
     */
    public int getNumberOfLifts() {
        return numberOfLifts;
    }

    /**
     * Sets number of lifts.
     *
     * @param numberOfLifts the number of lifts
     */
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
