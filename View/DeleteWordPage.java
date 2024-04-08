package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DeleteWordPage extends JFrame{
    private JLabel typeDictionaryLabel;
    private JComboBox<String> typeDictionaryComboBox;

    private JLabel wordLabel;
    private JTextField wordField;
    private JButton deleteWordButton;

    public DeleteWordPage() {
        initComponent();
    }

    void initComponent() {
        setTitle("Delete Word");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        typeDictionaryLabel = new JLabel("Loại từ điển:");
        typeDictionaryComboBox = new JComboBox<>();

        wordLabel = new JLabel("Nhập từ cần xóa:");
        wordField = new JTextField(20);
        deleteWordButton = new JButton("Xóa từ");

        typeDictionaryComboBox.addItem("Anh - Việt");
        typeDictionaryComboBox.addItem("Việt - Anh");

        // Tạo SpringLayout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(360,180);

        // Thêm các thành phần vào panel
        panel.add(wordLabel);
        panel.add(wordField);
        panel.add(deleteWordButton);

        panel.add(typeDictionaryLabel);
        panel.add(typeDictionaryComboBox);

        // Cài đặt vị trí các thành phần
        layout.putConstraint(SpringLayout.WEST, typeDictionaryLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeDictionaryLabel, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, typeDictionaryComboBox, 30, SpringLayout.EAST, typeDictionaryLabel);
        layout.putConstraint(SpringLayout.NORTH, typeDictionaryComboBox, 18, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, wordLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, wordLabel, 20, SpringLayout.SOUTH, typeDictionaryLabel);

        layout.putConstraint(SpringLayout.WEST, wordField, 0, SpringLayout.WEST, typeDictionaryComboBox);
        layout.putConstraint(SpringLayout.NORTH, wordField, 20, SpringLayout.SOUTH, typeDictionaryLabel);

        layout.putConstraint(SpringLayout.EAST, deleteWordButton, 0, SpringLayout.EAST, wordField);
        layout.putConstraint(SpringLayout.NORTH, deleteWordButton, 20, SpringLayout.SOUTH, wordField);

        // Set trạng thái button
        deleteWordButton.setEnabled(false);

        // Thêm listener cho các thành phần
        wordField.getDocument().addDocumentListener(documentListener);
        
        // Thêm panel vào frame
        add(panel);
        setSize(360, 180);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Listener
    public void addDeleteWordButtonListener(java.awt.event.ActionListener listener) {
        deleteWordButton.addActionListener(listener);
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
        if (wordField.getText().length() > 0) {
            deleteWordButton.setEnabled(true);
        } else {
            deleteWordButton.setEnabled(false);
        }
    }

    // Getter
    public String getTypeDictionary() {
        return (String) typeDictionaryComboBox.getSelectedItem();
    }
    public String getWordField() {
        return wordField.getText();
    }
}
