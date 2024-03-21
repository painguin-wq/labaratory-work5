package dev.infochem.application.model;

import java.util.Objects;

public class Coordinates {
    private Long x;
    private int y;

    public Coordinates(Long x, int y) {
        setX(x);
        setY(y);
    }
    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        if (x == null || x < -336) {
            throw new IllegalArgumentException("Field x should be not null and more than -336");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

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
}
