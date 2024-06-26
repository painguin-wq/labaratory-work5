package dev.infochem.application.model;

import java.util.Objects;

/**
 * The type Coordinates.
 */
public class Coordinates implements Comparable<Coordinates>{
    private Long x;
    private int y;

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(Long x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Long getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(Long x) {
        if (x == null || x < -336) {
            throw new IllegalArgumentException("Field x should be not null and more than -336");
        }
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y && Objects.equals(x, that.x);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates o) {
        int compare = 0;
        compare += (int) (getX() - o.getX());
        compare += getY() - o.getY();
        return compare;
    }
}
