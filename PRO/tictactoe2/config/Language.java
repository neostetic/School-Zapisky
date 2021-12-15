package cz.polacek.tictactoe2.config;

import java.util.Map;

public interface Language {
    String selectLanguage = "Choose your language: ";
    String selectGameFieldRows = "SELECT_GAME_FIELD_ROWS: ";
    String selectGameFieldColumns = "SELECT_GAME_FIELD_COLUMNS: ";
    String player1start = "PLAYER1_START";
    String player2start = "PLAYER2_START";
    String player1wins = "PLAYER1_WINS";
    String player2wins = "PLAYER2_WINS";
    String selectRow = "SELECT_ROW";
    String selectColumn = "SELECT_COLUMN";

    Map<String, String> getLocalization();

    String localized(String id);
}
