package cz.polacek.casino;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int inventory = 10000;
    public static Scanner sc = new Scanner(System.in, "Windows-1250");
    public static int[] black = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
    public static int[] red = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
    public static int[] green = {0};
    public static int bidValue;
    public static int bidColor;

    public static int random() {
        return new Random().nextInt(37);
    }

    public static void main(String[] args) {
        rouletteInfo();
        roulette();
    }

    public static void rouletteInfo() {
        System.out.println(
                "-----*/HELP/*-----\n" +
                "|    0 - green   |\n" +
                "|    1 - black   |\n" +
                "|    2 - red     |\n" +
                "------------------\n"
        );
    }

    public static void roulette() {
        // System.out.println(Thread.currentThread().getStackTrace().length);
        System.out.println("- Your balance: "+inventory);
        System.out.print("Your bid: "); bidValue = sc.nextInt();
        System.out.print("Color bid: "); bidColor = sc.nextInt();
        bid();
    }

    public static void bid() {
        if (bidColor == 0) {
            for (int j : green) {
                if (random() == j) {
                    win();
                    break;
                }
            }
            lose();
        }
        if (bidColor == 1) {
            for (int j : black) {
                if (random() == j) {
                    win();
                    break;
                }
            }
            lose();
        }
        if (bidColor == 2) {
            for (int j : red) {
                if (random() == j) {
                    win();
                    break;
                }
            }
            lose();
        }

    }

    public static void lose() {
        inventory -= bidValue;
        System.out.println("\nYou lose!\n");
        roulette();
    }

    public static void win() {
        inventory += bidValue;
        System.out.println("\nYou won!\n");
        roulette();
    }

}
