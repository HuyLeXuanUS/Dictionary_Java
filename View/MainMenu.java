package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JLabel titleLabel;
    private JLabel enterLabel;
    private JLabel selectLabel;

    private JTextField enterField;
    private JComboBox<String> selectBox;
    
    private JButton searchButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton favoriteButton;
    private JButton historyButton;

    public MainMenu() {
        initComponent();
    }

    private void initComponent() {
        setTitle("Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Dictionary by US-495");
        enterLabel = new JLabel("Nhập từ tra cứu:");
        selectLabel = new JLabel("Chọn từ điển:");

        enterField = new JTextField();
        selectBox = new JComboBox<String>();

        searchButton = new JButton("Tra cứu");
        addButton = new JButton("Thêm từ mới");      
        removeButton = new JButton("Xóa từ");
        favoriteButton = new JButton("Từ yêu thích");
        historyButton = new JButton("Lịch sử tra cứu");

        // Custom component
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titleLabel.setForeground(Color.RED);

        selectBox.addItem("Anh - Việt");
        selectBox.addItem("Việt - Anh");
        
        // Tạo SpringLayout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(450,300);

        // Thêm các component vào panel
        panel.add(titleLabel);
        panel.add(enterLabel);
        panel.add(selectLabel);

        panel.add(enterField);
        panel.add(selectBox);

        panel.add(searchButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(favoriteButton);
        panel.add(historyButton);

        // Đặt vị trí các component
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, enterLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, enterLabel, 45, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, enterField, 20, SpringLayout.EAST, enterLabel);
        layout.putConstraint(SpringLayout.SOUTH, enterField, 48, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.EAST, enterField, 200, SpringLayout.EAST, enterLabel);

        layout.putConstraint(SpringLayout.EAST, searchButton, -20, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, searchButton, 50, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, searchButton, 20, SpringLayout.EAST, enterField);

        layout.putConstraint(SpringLayout.EAST, selectLabel, 0, SpringLayout.EAST, enterLabel);
        layout.putConstraint(SpringLayout.SOUTH, selectLabel, 45, SpringLayout.SOUTH, enterLabel);

        layout.putConstraint(SpringLayout.WEST, selectBox, 20, SpringLayout.EAST, selectLabel);
        layout.putConstraint(SpringLayout.SOUTH, selectBox, 48, SpringLayout.SOUTH, enterLabel);

        layout.putConstraint(SpringLayout.WEST, addButton, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, addButton, 50, SpringLayout.SOUTH, selectBox);
        layout.putConstraint(SpringLayout.EAST, addButton, 190, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, removeButton, -190, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, removeButton, 50, SpringLayout.SOUTH, selectBox);
        layout.putConstraint(SpringLayout.EAST, removeButton, -30, SpringLayout.EAST, panel);

        layout.putConstraint(SpringLayout.WEST, favoriteButton, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, favoriteButton, 20, SpringLayout.SOUTH, addButton);
        layout.putConstraint(SpringLayout.EAST, favoriteButton, 190, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, historyButton, -190, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, historyButton, 20, SpringLayout.SOUTH, addButton);
        layout.putConstraint(SpringLayout.EAST, historyButton, -30, SpringLayout.EAST, panel);

        // Listener
        enterField.getDocument().addDocumentListener(documentListener);

        // Set trạng thái button
        searchButton.setEnabled(false);

        // Hiển thị frame
        add(panel);
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Listener
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    public void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }
    public void addFavoriteButtonListener(ActionListener listener) {
        favoriteButton.addActionListener(listener);
    }
    public void addHistoryButtonListener(ActionListener listener) {
        historyButton.addActionListener(listener);
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
        if (enterField.getText().length() > 0) {
            searchButton.setEnabled(true);
        } else {
            searchButton.setEnabled(false);
        }
    }

    // Get EnterField String
    public String getEnterField() {
        return enterField.getText();
    }

    // Get SelectBox String
    public String getSelectBoxString() {
        return selectBox.getSelectedItem().toString();
    }

    // Set Enable Page
    public void setEnablePage(boolean enable) {
        this.setEnabled(enable);
    }
}
