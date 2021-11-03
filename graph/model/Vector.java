package cz.polacek.graph.model;

import java.util.Arrays;

public class Vector {

    private int[] cords;

    public Vector(int[] cords) {
        this.cords = cords;
    }

    public int[] getCords() {
        return cords;
    }

    public void setCords(int[] cords) {
        this.cords = cords;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "cords=" + Arrays.toString(cords) +
                '}';
    }
}
