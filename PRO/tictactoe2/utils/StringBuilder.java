package cz.polacek.tictactoe2.utils;

import java.io.*;
import java.util.Scanner;

public class StringBuilder {

    public void WriteFile(File file, String string) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(string + "\n");
        writer.flush();
        writer.close();
    }

    public void ReadFile(File file) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            System.out.println(string);
        }

    }

}
