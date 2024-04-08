package Model.Record;

import java.io.Serializable;
import java.util.*;

public class Definition implements Serializable{
    private static final long serialVersionUID = 1L; 
    private String partOfSpeech;
    private List<ExampleForDefinition> exForMeanings;

    // Getter and Setter
    public String getPartOfSpeech() {
        return partOfSpeech;
    }
    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
    public void addPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech += partOfSpeech;
    }

    public List<ExampleForDefinition> getExamplesForDefinitions() {
        return exForMeanings;
    }
    public void setMeaning(List<ExampleForDefinition> meaning) {
        this.exForMeanings = meaning;
    }

    // Add meaning
    public void addExampleForDefination(ExampleForDefinition meaning) {
        if (exForMeanings == null) {
            exForMeanings = new ArrayList<>();
        }
        exForMeanings.add(meaning);
    }
    
}
