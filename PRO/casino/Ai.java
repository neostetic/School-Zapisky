package cz.polacek.casino;

public class Ai {

    public static void main(String[] args) {
        aiRoulette();
    }

    public static int aiInventory = 10000;
    public static int aiBid;
    public static double winRate;
    public static int aiRound = 0;
    public static double aiSuccess = 0;
    public static int aiRandom;

    public static void aiRoulette() {
        do {
            aiRound++;
            winRate = aiSuccess/aiRound*100;
            aiBid = aiInventory/10;
            if (aiBid == 0) {aiBid = 1;}
            System.out.println("\nINSTANCE " + aiRound);
            System.out.println("- AI's balance: " + aiInventory);
            System.out.println("- AI's bid: " + aiBid);
            System.out.println("- AI's win rate: " + winRate + "%");
            bid();
        } while (aiInventory > 0);
        System.out.println("\nAI lost all your money!");
    }

    public static void bid() {
        aiRandom = Main.random();
        System.out.print("- Rolled: " + aiRandom);
        for (int j : Main.black) {
            if (aiRandom == j) {
                win();
                return;
            }
        }
        lose();
    }

    public static void win() {
        aiInventory += aiBid;
        aiSuccess++;
        System.out.println(" - won");
    }

    public static void lose() {
        aiInventory -= aiBid;
        System.out.println(" - lost");
    }

}