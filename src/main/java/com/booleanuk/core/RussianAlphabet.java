package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public class RussianAlphabet implements Alphabet{
    public Map<String, Integer> getLetterScores() {
        return new HashMap<>(){{
            put("a", 1);
            put("б", 1);
            put("в", 1);
            put("г", 1);
            put("д", 1);

            put("е", 2);
            put("ё", 2);
            put("ж", 2);
            put("з", 2);
            put("и", 2);


            put("й", 3);
            put("к", 3);
            put("л", 3);
            put("м", 3);
            put("н", 3);

            put("о", 4);
            put("п", 4);
            put("р", 4);
            put("с", 4);
            put("т", 4);
            put("у", 4);
            put("ф", 4);

            put("х", 5);
            put("ц", 5);
            put("ч", 5);
            put("ш", 5);

            put("щ", 8);
            put("ъ", 8);
            put("ы", 8);

            put("ь", 10);
            put("э", 10);
            put("ю", 10);
        }};
    }
}
