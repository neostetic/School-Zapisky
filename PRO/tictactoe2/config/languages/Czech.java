package cz.polacek.tictactoe2.config.languages;

import cz.polacek.tictactoe2.config.Language;

import java.util.HashMap;
import java.util.Map;

public class Czech extends AbstractLanguage {

    enum CzechMessages {
        SELECT_LANGUAGE(Language.selectLanguage, "Vyber si jazyk: "),
        SELECT_GAME_FIELD_ROWS(Language.selectGameFieldRows, "Kolik řádků má mít hrací plocha: "),
        SELECT_GAME_FIELD_COLUMNS(Language.selectGameFieldColumns, "Kolik sloupců má mít hrací plocha: "),
        PLAYER1_STARTS(Language.player1start, "Hráč č.1 začíná"),
        PLAYER2_STARTS(Language.player2start, "Hráč č.2 začíná"),
        PLAYER1_WINS(Language.player1wins, "Hráč č.1 vyhrál"),
        PLAYER2_WINS(Language.player2wins, "Hráč č.2 vyhrál"),
        SELECT_ROW(Language.selectRow, "Vyber si řádek: "),
        SELECT_COLUMN(Language.selectColumn, "Vyber si sloupec: ");

        private String key;
        private String value;

        CzechMessages(String key, String value) {
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
        for(CzechMessages message : CzechMessages.values()) {
            messages.put(message.key, message.value);
        }
        return messages;
    }

}
