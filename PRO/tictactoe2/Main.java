package cz.polacek.tictactoe2;

import cz.polacek.tictactoe2.config.Engine;
import cz.polacek.tictactoe2.utils.StringBuilder;

import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("./src/cz/polacek/tictactoe2/records/local.csv");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.WriteFile(file, new Date().toString());
        stringBuilder.ReadFile(file);
        new Engine().startGame();
    }

}
