package cz.polacek.zaci;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "Windows-1250");
        System.out.print("How many times: ");
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            System.out.print("Grades"+i+": ");
            String diameter = sc.next();
            System.out.println("Diameter"+i+": "+calcDiameter(diameter));
        }

    }

    public static int calcDiameter(String stringDiameter) {
        int diameter = 0;
        String[] splitString = stringDiameter.split(" ");
        for (String s : splitString) {
            int number = Integer.parseInt(s);
            diameter += number;
        }
        return diameter / splitString.length;
    }

}
