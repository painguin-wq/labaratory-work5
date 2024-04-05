package dev.infochem.application.model;

/**
 * The type House factory.
 */
public class HouseFactory {
    private House house;

    /**
     * Create house factory.
     *
     * @param year          the year
     * @param numberOfLifts the number of lifts
     * @return the house factory
     */
    public HouseFactory create(int year, int numberOfLifts) {
        house = new House();
        house.setYear(year);
        house.setNumberOfLifts(numberOfLifts);
        return this;
    }

    /**
     * Add name house factory.
     *
     * @param name the name
     * @return the house factory
     */
    public HouseFactory addName(String name) {
        house.setName(name);
        return this;
    }

    /**
     * Build house.
     *
     * @return the house
     */
    public House build() {
        House houseBuild = house;
        house = null;
        return houseBuild;
    }
}
