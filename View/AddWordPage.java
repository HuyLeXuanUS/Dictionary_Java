package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

public class AddWordPage extends JFrame{
    private JLabel typeDictionaryLabel;
    private JLabel wordLabel;
    private JLabel meaningLabel;

    private JComboBox<String> typeDictionaryComboBox;
    private JTextField wordField;
    private JTextArea meaningField;

    private JButton addWordButton;

    public AddWordPage() {
        initComponent();
    }

    void initComponent() {
        setTitle("Add Word");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        typeDictionaryLabel = new JLabel("Loại từ điển:");
        wordLabel = new JLabel("Nhập từ:");
        meaningLabel = new JLabel("Nhập nghĩa: ");

        typeDictionaryComboBox = new JComboBox<>();
        wordField = new JTextField(20);
        meaningField = new JTextArea();
        addWordButton = new JButton("Thêm từ");

        meaningField.setRows(5);
        meaningField.setColumns(20);

        typeDictionaryComboBox.addItem("Anh - Việt");
        typeDictionaryComboBox.addItem("Việt - Anh");

        // Tạo SpringLayout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(350,300);

        // Thêm các thành phần vào panel
        panel.add(typeDictionaryLabel);
        panel.add(wordLabel);
        panel.add(meaningLabel);
        panel.add(typeDictionaryComboBox);
        panel.add(wordField);
        panel.add(meaningField);
        panel.add(addWordButton);

        // Cài đặt vị trí các thành phần
        layout.putConstraint(SpringLayout.WEST, typeDictionaryLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeDictionaryLabel, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, typeDictionaryComboBox, 20, SpringLayout.EAST, typeDictionaryLabel);
        layout.putConstraint(SpringLayout.NORTH, typeDictionaryComboBox, 18, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, wordLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, wordLabel, 20, SpringLayout.SOUTH, typeDictionaryLabel);

        layout.putConstraint(SpringLayout.WEST, wordField, 0, SpringLayout.WEST, typeDictionaryComboBox);
        layout.putConstraint(SpringLayout.NORTH, wordField, 20, SpringLayout.SOUTH, typeDictionaryLabel);

        layout.putConstraint(SpringLayout.WEST, meaningLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, meaningLabel, 20, SpringLayout.SOUTH, wordLabel);

        layout.putConstraint(SpringLayout.WEST, meaningField, 0, SpringLayout.WEST, typeDictionaryComboBox);
        layout.putConstraint(SpringLayout.NORTH, meaningField, 20, SpringLayout.SOUTH, wordField);

        layout.putConstraint(SpringLayout.EAST, addWordButton, 0, SpringLayout.EAST, meaningField);
        layout.putConstraint(SpringLayout.NORTH, addWordButton, 20, SpringLayout.SOUTH, meaningField);

        // Set trạng thái button
        addWordButton.setEnabled(false);

        // Thêm listener cho các thành phần
        wordField.getDocument().addDocumentListener(documentListener);
        meaningField.getDocument().addDocumentListener(documentListener);
        
        // Cài đặt vị trí cho Page
        add(panel);
        setSize(350, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Listeners
    public void addAddWordButtonListener(ActionListener listener) {
        addWordButton.addActionListener(listener);
    }

    // DocumentListener
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }
    };

    private void updateButtonState() {
        if (wordField.getText().length() > 0 && meaningField.getText().length() > 0) {
            addWordButton.setEnabled(true);
        } else {
            addWordButton.setEnabled(false);
        }
    }

    // Getters
    public String getTypeDictionary() {
        return (String) typeDictionaryComboBox.getSelectedItem();
    }
    public String getWord() {
        return wordField.getText();
    }
    public String getMeaning() {
        return meaningField.getText();
    }
}
