package cz.polacek.graph;

import cz.polacek.graph.model.Point;
import cz.polacek.graph.model.Point2D;
import cz.polacek.graph.model.Point3D;
import cz.polacek.graph.model.Vector;

public class Main {

    public static void main(String[] args) {

        Point pointA = new Point(1,1,5);
        Point2D pointB = new Point2D(4,8);
        Point3D pointC = new Point3D(4,8,4);

        Vector vector = pointA.getVector(pointB);
        System.out.println(pointA.toString());
        System.out.println(pointB.toString());
        System.out.println(pointC.toString());
        System.out.println(vector.toString());

    }
}
