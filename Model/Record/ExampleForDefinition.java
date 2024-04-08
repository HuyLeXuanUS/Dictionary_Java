package Model.Record;

import java.io.Serializable;
import java.util.*;

public class ExampleForDefinition implements Serializable{
    private static final long serialVersionUID = 1L; 
    private String meaning;
    private List<String> examples;

    // Getter and Setter
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<String> getExamples() {
        return examples;
    }
    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    // Add example
    public void addExample(String example) {
        if (examples == null) {
            examples = new ArrayList<>();
        }
        examples.add(example);
    }
}
