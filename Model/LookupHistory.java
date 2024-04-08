package Model;

public class LookupHistory {
    String word;
    String type;
    int count;

    public LookupHistory(String word, String type, int count) {
        this.word = word;
        this.type = type;
        this.count = count;
    }

    public String getWord() {
        return word;
    }
    public String getType() {
        return type;
    }
    public int getCount() {
        return count;
    }

    // Set count of word
    public void setCount(int count) {
        this.count = count;
    }
}

