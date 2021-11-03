package cz.polacek.graph.model;

import java.util.Arrays;

public class Point {

    private int[] cords;

    public Point(int... cords) {
        this.cords = cords;
    }

    public Vector getVector(Point pointB) {
        int[] vector = new int[this.cords.length];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = pointB.getCords()[i] - this.getCords()[i];
        }
        return new Vector(vector);
    }

    public int[] getCords() {
        return cords;
    }

    public void setCords(int[] cords) {
        this.cords = cords;
    }

    @Override
    public String toString() {
        return "Point{" +
                "cords=" + Arrays.toString(cords) +
                '}';
    }
}
