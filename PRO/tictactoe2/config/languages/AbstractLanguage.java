package cz.polacek.tictactoe2.config.languages;

import cz.polacek.tictactoe2.config.Language;

public abstract class AbstractLanguage implements Language {

    @Override
    public String localized(String id) {
        return getLocalization().get(id);
    }
}
