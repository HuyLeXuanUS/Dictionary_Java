package Model.Record;

import java.io.Serializable;
import java.util.*;

public class Meaning implements Serializable{
    private static final long serialVersionUID = 1L; 
    private String pronuncition;
    private List<Definition> definitions;

    // Constructor
    public Meaning() {
        this.pronuncition = "";
        this.definitions = new ArrayList<>();
    }
    
    public Meaning(String meaning) {
        this.pronuncition = "";
        this.definitions = new ArrayList<>();

        Definition definition = new Definition();
        definition.setPartOfSpeech("None");
        ExampleForDefinition exampleForDefinition = new ExampleForDefinition();

        exampleForDefinition.setMeaning(meaning);
        definition.addExampleForDefination(exampleForDefinition);
        addMeaning(definition);
    }

    // Getter and Setter
    public String getPronunciation() {
        return pronuncition;
    }

    public void setPronuncition(String pronuncition) {
        this.pronuncition = pronuncition;
    }

    public List<Definition> getMeanings() {
        return definitions;
    }

    public void setMeanings(List<Definition> meanings) {
        this.definitions = meanings;
    }

    // Add meaning
    public void addMeaning(Definition meaning) {
        if (definitions == null) {
            definitions = new ArrayList<>();
        }
        definitions.add(meaning);
    }

    // Get meaning of word
    public void getMeaningEtoV(String meaning, String word) {
        String[] lines = meaning.split("\n");

        // Get pronuncition
        String firstLine = lines[0];
        String pronuncition = firstLine.substring(word.length() + 1);

        if (pronuncition.isEmpty()) {
            this.pronuncition = "No pronuncition";
        } else {
            this.pronuncition = pronuncition;
        }

        // Get meaning
        int checkline = 1;
        if (lines.length == 1) {
            return;
        }
        String lineString = lines[checkline];

        while (checkline < lines.length) {
            Definition definition = new Definition();
            if (lineString.charAt(0) == '*') {
                definition.setPartOfSpeech(lineString.substring(1).trim());
                ++checkline;
                if (checkline == lines.length) {
                    addMeaning(definition);
                    break;
                }
                lineString = lines[checkline];
                while (lineString.charAt(0) != '-') {
                    definition.addPartOfSpeech("  " + lineString);
                    ++checkline;
                    if (checkline == lines.length) {
                        addMeaning(definition);
                        break;
                    }
                    lineString = lines[checkline];
                }
            } else if (lineString.charAt(0) == '!') {
                definition.setPartOfSpeech("Special:  " + lineString.substring(1).trim());
                ++checkline;
                if (checkline == lines.length) {
                    break;
                }
                lineString = lines[checkline];
            } else {
                definition.setPartOfSpeech("None");
            }

            while (lineString.charAt(0) != '*' && lineString.charAt(0) != '!') {
                if (checkline == lines.length) {
                    break;
                }
                ExampleForDefinition exampleForDefinition = new ExampleForDefinition();
                if (lineString.charAt(0) == '-' || lineString.charAt(0) == '+') {
                    exampleForDefinition.setMeaning(lineString.substring(1).trim());
                    checkline++;
                    if (checkline == lines.length) {
                        definition.addExampleForDefination(exampleForDefinition);
                        break;
                    }
                    lineString = lines[checkline];
                }
                while (lineString.charAt(0) == '=') {
                    exampleForDefinition.addExample(lineString.substring(1).trim());
                    checkline++;
                    if (checkline == lines.length) {
                        definition.addExampleForDefination(exampleForDefinition);
                        break;
                    }
                    lineString = lines[checkline];
                }
                definition.addExampleForDefination(exampleForDefinition);
            }

            addMeaning(definition);
        }
    }

    public void getMeaningVtoE(String meaning, String word) {
        String[] lines = meaning.split("\n");
        this.pronuncition = "No pronuncition";

        int checkline = 1;
        if (lines.length == 1) {
            return;
        }
        String lineString = lines[checkline];

        while (checkline < lines.length) {
            Definition definition = new Definition();
            if (lineString.charAt(0) == '*') {
                definition.setPartOfSpeech(lineString.substring(1).trim());
                ++checkline;
                if (checkline == lines.length) {
                    addMeaning(definition);
                    break;
                }
                lineString = lines[checkline];
            } else if (lineString.charAt(0) == '#') {
                definition.setPartOfSpeech("Synonyms: ");;
                ++checkline;
                if (checkline == lines.length) {
                    break;
                }
                lineString = lines[checkline];
            } else {
                definition.setPartOfSpeech("None");
            }

            while (lineString.charAt(0) != '*' && lineString.charAt(0) != '#') {
                if (checkline == lines.length) {
                    break;
                }
                ExampleForDefinition exampleForDefinition = new ExampleForDefinition();
                if (lineString.charAt(0) == '-' || lineString.charAt(0) == '+') {
                    exampleForDefinition.setMeaning(lineString.substring(1).trim());
                    checkline++;
                    if (checkline == lines.length) {
                        definition.addExampleForDefination(exampleForDefinition);
                        break;
                    }
                    lineString = lines[checkline];
                }
                while (lineString.charAt(0) == '=') {
                    exampleForDefinition.addExample(lineString.substring(1).trim());
                    checkline++;
                    if (checkline == lines.length) {
                        definition.addExampleForDefination(exampleForDefinition);
                        break;
                    }
                    lineString = lines[checkline];
                }
                definition.addExampleForDefination(exampleForDefinition);
            }
            addMeaning(definition);
        }
    }

}