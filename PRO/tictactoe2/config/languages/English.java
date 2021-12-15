package cz.polacek.tictactoe2.config.languages;

import cz.polacek.tictactoe2.config.Language;

import java.util.HashMap;
import java.util.Map;

public class English extends AbstractLanguage {

    enum EnglishMessages {
        SELECT_LANGUAGE(Language.selectLanguage, "Choose your language: "),
        SELECT_GAME_FIELD_ROWS(Language.selectGameFieldRows, "How many rows will the playfield have: "),
        SELECT_GAME_FIELD_COLUMNS(Language.selectGameFieldColumns, "How many columns will the playfield have: "),
        PLAYER1_STARTS(Language.player1start, "Player 1 starts"),
        PLAYER2_STARTS(Language.player2start, "Player 2 starts"),
        PLAYER1_WINS(Language.player1wins, "Player 1 wins"),
        PLAYER2_WINS(Language.player2wins, "Player 2 wins"),
        SELECT_ROW(Language.selectRow, "Select row: "),
        SELECT_COLUMN(Language.selectColumn, "Select column: ");

        private String key;
        private String value;

        EnglishMessages(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public Map<String, String> getLocalization() {
        Map<String, String> messages = new HashMap<>();
        for(EnglishMessages message : EnglishMessages.values()) {
            messages.put(message.key, message.value);
        }
        return messages;
    }

}
