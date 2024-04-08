package Model;

import java.io.*;
import java.time.LocalDate;

public class History implements Serializable{
    private static final long serialVersionUID = 123L;
    String word;
    String type;
    LocalDate date;
    
    public History(String word, String type, LocalDate date) {
        this.word = word;
        this.type = type;
        this.date = date;
    }

    public String getWord() {
        return word;
    }
    public String getType() {
        return type;
    }
    public LocalDate getDate() {
        return date;
    }
}
