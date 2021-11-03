package cz.polacek.casino;

public class Ai {

    public static void main(String[] args) {

        aiBid();

    }

    public static void aiBid() {
        System.out.println("- AI's balance: " + Main.inventory);
        System.out.print("AI's bid: ");
        Main.bidValue = Main.inventory/10;
        Main.bidColor = 2;
        Main.bid();
    }



}
