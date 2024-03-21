package dev.infochem.application.model;

public class HouseFactory {
    private House house;
    public HouseFactory create(int year, int numberOfLifts) {
        house = new House();
        house.setYear(year);
        house.setNumberOfLifts(numberOfLifts);
        return this;
    }

    public HouseFactory addName(String name) {
        house.setName(name);
        return this;
    }

    public House build() {
        House houseBuild = house;
        house = null;
        return houseBuild;
    }
}
