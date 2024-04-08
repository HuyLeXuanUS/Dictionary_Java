package Model.Record;

import java.io.Serializable;

public class Word implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String word;
    private Meaning meaning;

    public Word(String word, Meaning meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }
    public Meaning getMeaning() {
        return meaning;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }
}
