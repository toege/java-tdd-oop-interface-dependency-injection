package com.booleanuk.core;

import java.util.Map;

public class Scrabble {
    Map<String, Integer> engLetterScores;
    Map<String, Integer> rusLetterScores;
    Map<String, Integer> greLetterScores;

    public Scrabble() {
        Alphabet eng = new EngAlphabet();
        Alphabet rus = new EngAlphabet();
        Alphabet gre = new EngAlphabet();
        this.engLetterScores = eng.getLetterScores();
        this.rusLetterScores = rus.getLetterScores();
        this.greLetterScores = gre.getLetterScores();
    }

    public int score(String language, String word) {
        int total = 0;
        Map<String, Integer> letterScores;
        switch (language) {
            case "rus": {
                letterScores = rusLetterScores;
                break;
            }
            case "gre": {
                letterScores = greLetterScores;
                break;
            }
            default: {
                letterScores = engLetterScores;
                break;
            }
        }

        for (char ch : word.toCharArray()) {
            if (letterScores.containsKey(Character.toString(ch))) {
                int score = letterScores.get(Character.toString(ch));
                total += score;
            }
        }

        return total;
    }
}
