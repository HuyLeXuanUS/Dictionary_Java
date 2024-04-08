package View;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import Model.LookupHistory;

public class HistoryPage extends JFrame{
    private JLabel dateFrom;
    private JLabel dateTo;

    private JTextField dateFromField;
    private JTextField dateToField;

    private JButton searchButton;

    private JScrollPane jScrollPaneHistoryTable;
    private JTable HistoryTable;

    private String[] columnNames = {"Từ", "Loại", "Tần suất tìm kiếm"};

    public HistoryPage() {
        initComponent();
    }

    public void initComponent() {
        setTitle("History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dateFrom = new JLabel("Từ ngày: (dd/mm/yyyy)");
        dateTo = new JLabel("Đến ngày: (dd/mm/yyyy)");

        searchButton = new JButton("Tra cứu");

        dateFromField = new JTextField(10);
        dateToField = new JTextField(10);

        // Tạo bảng lịch sử
        HistoryTable = new JTable();
        jScrollPaneHistoryTable = new JScrollPane();

        // Cài đặt bảng lịch sử
        HistoryTable.setModel(new DefaultTableModel(
                new Object[][]{},
                columnNames
        ));
        jScrollPaneHistoryTable.setViewportView(HistoryTable);
        jScrollPaneHistoryTable.setPreferredSize(new java.awt.Dimension(400, 300));

        // Tạo Springlayout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(450,450);

        // Thêm các thành phần vào panel
        panel.add(dateFrom);
        panel.add(dateFromField);
        panel.add(dateTo);
        panel.add(dateToField);
        panel.add(searchButton);
        panel.add(jScrollPaneHistoryTable);

        // Cài đặt vị trí các thành phần
        layout.putConstraint(SpringLayout.WEST, dateFrom, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateFrom, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, dateFromField, 10, SpringLayout.EAST, dateFrom);
        layout.putConstraint(SpringLayout.NORTH, dateFromField, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, dateTo, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateTo, 20, SpringLayout.SOUTH, dateFromField);

        layout.putConstraint(SpringLayout.WEST, dateToField, 0, SpringLayout.WEST, dateFromField);
        layout.putConstraint(SpringLayout.NORTH, dateToField, 20, SpringLayout.SOUTH, dateFromField);

        layout.putConstraint(SpringLayout.WEST, searchButton, 20, SpringLayout.EAST, dateToField);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 20, SpringLayout.NORTH, dateFromField);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneHistoryTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneHistoryTable, 20, SpringLayout.SOUTH, dateToField);
        
        // Set trạng thái button
        searchButton.setEnabled(false);

        // Thêm listener cho các thành phần
        dateFromField.getDocument().addDocumentListener(documentListener);
        dateToField.getDocument().addDocumentListener(documentListener);
        
        // Cài đặt vị trí cho Page
        add(panel);
        setSize(450, 450);
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Show lịch sử tìm kiếm
    public void showHistory(List<LookupHistory> historyList) {
        int size = historyList.size();
        Object[][] history = new Object[size][3];  
        
        for (int i = 0; i < size; i++) {
            history[i][0] = historyList.get(i).getWord();
            history[i][1] = historyList.get(i).getType();
            history[i][2] = historyList.get(i).getCount();
        }

        HistoryTable.setModel(new DefaultTableModel(
                history,
                columnNames
        ));
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
        if (dateFromField.getText().length() > 0 && dateToField.getText().length() > 0) {
            searchButton.setEnabled(true);
        } else {
            searchButton.setEnabled(false);
        }
    }

    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public String getDateFrom() {
        return dateFromField.getText();
    }

    public String getDateTo() {
        return dateToField.getText();
    }
}
