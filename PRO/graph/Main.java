package cz.polacek.graph;

import cz.polacek.graph.model.Point;
import cz.polacek.graph.model.Vector;

public class Main {

    public static void main(String[] args) {

        Point pointA = new Point(1,1,5);
        Point pointB = new Point(4,8,7);

        Vector vector = pointA.getVector(pointB);
        System.out.println(pointA.toString());
        System.out.println(pointB.toString());
        System.out.println(vector.toString());

    }
}
