package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerVtoE;
import Model.Record.Definition;
import Model.Record.ExampleForDefinition;
import Model.Record.Meaning;

public class SearchWordPage extends JFrame{
    private String word;
    private String typeDictionary;

    private JLabel titleLabel;
    private JLabel pronunciationLabel;
    private List<JLabel> partOfSpeechLabels;
    private List<JLabel> meaningLabels;
    private List<JLabel> exampleLabels;
    private JButton favoriteButton;

    private static final int MAX_LABEL_WIDTH = 450;

    // Getters
    public String getWord() {
        return word;
    }
    public String getTypeDictionary() {
        return typeDictionary;
    }

    public SearchWordPage(String word, Meaning meaning, String typeDictionary) {
        this.word = word;
        this.typeDictionary = typeDictionary;

        setTitle("Search Word");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("<html>&nbsp;&nbsp;&nbsp;<font color='red' size='+1'>" + word + "</font></html>");
        pronunciationLabel = new JLabel("Phát âm:  " + meaning.getPronunciation());

        contentPanel.add(titleLabel);
        contentPanel.add(pronunciationLabel);

        partOfSpeechLabels = new ArrayList<>();
        meaningLabels = new ArrayList<>();
        exampleLabels = new ArrayList<>();

        favoriteButton = new JButton("Thêm từ yêu thích");

        for (Definition definition : meaning.getMeanings()) {
            JLabel partOfSpeechLabel = new JLabel("<html>&nbsp;&nbsp;<font color='blue' size='+0'>" + definition.getPartOfSpeech() + "</font></html>");
            partOfSpeechLabels.add(partOfSpeechLabel);
            contentPanel.add(partOfSpeechLabel);

            if (definition.getExamplesForDefinitions() == null) {
                continue;
            }

            for (ExampleForDefinition exampleForDefinition : definition.getExamplesForDefinitions()) {
                List<JLabel> meaningLabelsTemp = splitLabel("-  " + exampleForDefinition.getMeaning(), MAX_LABEL_WIDTH);
                for (JLabel meaningLabel : meaningLabelsTemp) {
                    meaningLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    meaningLabels.add(meaningLabel);
                    contentPanel.add(meaningLabel);
                }

                if (exampleForDefinition.getExamples() == null) {
                    continue;
                } 
                for (String example : exampleForDefinition.getExamples()) {
                    List<JLabel> exampleLabelsTemp = splitLabel("Ex: " + splitExample(example), MAX_LABEL_WIDTH);
                    for (JLabel exampleLabel : exampleLabelsTemp) {
                        exampleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        exampleLabels.add(exampleLabel);
                        contentPanel.add(exampleLabel);
                    }
                }
            }
        }

        contentPanel.add(favoriteButton);

        // Set enable button
        if (typeDictionary.equals("Anh - Việt")) {
            ManagerEtoV managerEtoV = new ManagerEtoV();
            if (managerEtoV.isFavoriteWord(word)) {
                favoriteButton.setEnabled(false);
            } else {
                favoriteButton.setEnabled(true);
            }
        } else {
            ManagerVtoE managerVtoE = new ManagerVtoE();
            if (managerVtoE.isFavoriteWord(word)) {
                favoriteButton.setEnabled(false);
            } else {
                favoriteButton.setEnabled(true);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(500, 600)); 
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<JLabel> splitLabel(String text, int maxWidth) {
        List<JLabel> labels = new ArrayList<>();
        JLabel currentLabel = new JLabel();

        StringBuilder currentText = new StringBuilder();
        for (char c : text.toCharArray()) {
            currentText.append(c);
            currentLabel.setText(currentText.toString());
            if (currentLabel.getPreferredSize().getWidth() > maxWidth) {
                labels.add(currentLabel);
                currentLabel = new JLabel();
                currentText = new StringBuilder();
            }
        }

        if (currentText.length() > 0) {
            currentLabel.setText(currentText.toString());
            labels.add(currentLabel);
        }
        return labels;
    }

    public static String splitExample(String input) {
        String[] parts = input.split("\\s*\\+\\s*");
        if (parts.length != 2) {
            return input;
        }
        String part1 = parts[0].trim();
        String part2 = parts[1].trim();
        return part1 + " -- " + part2;
    }

    // Listener
    public void addFavoriteButtonListener(ActionListener listener) {
        favoriteButton.addActionListener(listener);
    }

    // Set enable button
    public void setEnableButton(boolean enable) {
        favoriteButton.setEnabled(enable);
    }
}
