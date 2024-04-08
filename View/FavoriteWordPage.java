package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.util.List;

public class FavoriteWordPage extends JFrame{
    private JButton AtoZButton;
    private JButton ZtoAButton;
    private JButton searchButton;
    private JButton deleteButton;

    private JScrollPane jScrollPaneEtoVTable;
    private JTable EtoVTable;
    private JScrollPane jScrollPaneVtoETable;
    private JTable VtoETable;

    private String[] columnNamesEtoV = {"Anh - Việt"};
    private String[] columnNamesVtoE = {"Việt - Anh"};

    private String typeDictionary;
    private String searchWord;

    public FavoriteWordPage() {
        initComponent();
    }

    public void initComponent() {
        setTitle("Favorite Word");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AtoZButton = new JButton("A-Z");
        ZtoAButton = new JButton("Z-A");
        searchButton = new JButton("Tra cứu");
        deleteButton = new JButton("Xóa từ");

        // Tạo bảng Anh - Việt
        EtoVTable = new JTable();
        EtoVTable.setDefaultEditor(Object.class, null);;
        jScrollPaneEtoVTable = new JScrollPane();

        // Tạo bảng Việt - Anh
        VtoETable = new JTable();
        VtoETable.setDefaultEditor(Object.class, null);;
        jScrollPaneVtoETable = new JScrollPane();

        // Cài đặt bảng Anh - Việt
        EtoVTable.setModel(new DefaultTableModel(
                new Object[][]{},
                columnNamesEtoV
        ));
        EtoVTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneEtoVTable.setViewportView(EtoVTable);
        jScrollPaneEtoVTable.setPreferredSize(new Dimension(160, 300));

        // Cài đặt bảng Việt - Anh
        VtoETable.setModel(new DefaultTableModel(
                new Object[][]{},
                columnNamesVtoE
        ));
        VtoETable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneVtoETable.setViewportView(VtoETable);
        jScrollPaneVtoETable.setPreferredSize(new Dimension(160, 300));

        //
        EtoVTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Hủy chọn dòng trong bảng Việt - Anh
                VtoETable.getSelectionModel().clearSelection();
            }
        });

        // Thêm ListSelectionListener cho bảng Việt - Anh
        VtoETable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Hủy chọn dòng trong bảng Anh - Việt
                EtoVTable.getSelectionModel().clearSelection();
            }
        });

        // Tạo SpringLayout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(400,500);

        // Thêm các thành phần vào panel
        panel.add(AtoZButton);
        panel.add(ZtoAButton);
        panel.add(searchButton);
        panel.add(deleteButton);

        panel.add(jScrollPaneEtoVTable);
        panel.add(jScrollPaneVtoETable);

        // Cài đặt vị trí các thành phần
        layout.putConstraint(SpringLayout.WEST, jScrollPaneEtoVTable, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneEtoVTable, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.EAST, jScrollPaneVtoETable, -30, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneVtoETable, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, AtoZButton, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, AtoZButton, 20, SpringLayout.SOUTH, jScrollPaneEtoVTable);
        layout.putConstraint(SpringLayout.EAST, AtoZButton, 0, SpringLayout.EAST, jScrollPaneEtoVTable);

        layout.putConstraint(SpringLayout.EAST, ZtoAButton, -30, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, ZtoAButton, 20, SpringLayout.SOUTH, jScrollPaneVtoETable);
        layout.putConstraint(SpringLayout.WEST, ZtoAButton, 0, SpringLayout.WEST, jScrollPaneVtoETable);

        layout.putConstraint(SpringLayout.WEST, searchButton, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 20, SpringLayout.SOUTH, AtoZButton);
        layout.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, AtoZButton);

        layout.putConstraint(SpringLayout.EAST, deleteButton, -30, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 20, SpringLayout.SOUTH, ZtoAButton);
        layout.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, ZtoAButton);

        // Set trạng thái button
        searchButton.setEnabled(false);
        deleteButton.setEnabled(false);

        // Thêm Listener cho các button
        selectWordListener();

        // Cài đặt vị trí cho Page
        add(panel);
        setSize(400, 500);
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void showFavoriteList(List<String> favoriteWordsEtoV, List<String> favoriteWordsVtoE) {
        int size_EV = favoriteWordsEtoV.size();
        int size_VE = favoriteWordsVtoE.size();

        Object [][] word_EV = new Object[size_EV][1];
        Object [][] word_VE = new Object[size_VE][1];
        
        for (int i = 0; i < size_EV; i++) {
            word_EV[i][0] = favoriteWordsEtoV.get(i);
        }
        for (int i = 0; i < size_VE; i++) {
            word_VE[i][0] = favoriteWordsVtoE.get(i);
        }
        EtoVTable.setModel(new DefaultTableModel(word_EV, columnNamesEtoV));
        VtoETable.setModel(new DefaultTableModel(word_VE, columnNamesVtoE));
    }

    // Listener
    public void addAtoZButtonListener(ActionListener listener) {
        AtoZButton.addActionListener(listener);
    }
    public void addZtoAButtonListener(ActionListener listener) {
        ZtoAButton.addActionListener(listener);
    }
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    //
    public void setEnabledSearchButtonandDeleteButton(boolean status) {
        searchButton.setEnabled(status);
        deleteButton.setEnabled(status);
    }

    public void selectWordListener() {
        EtoVTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (EtoVTable.getSelectedRow() != -1) {
                        typeDictionary = "Anh - Việt";
                        searchWord = (String) EtoVTable.getValueAt(EtoVTable.getSelectedRow(), 0);
                        setEnabledSearchButtonandDeleteButton(true);
                    } else {
                        setEnabledSearchButtonandDeleteButton(false);  
                    }          
                } 
            }
        });

        VtoETable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (VtoETable.getSelectedRow() != -1) {
                        typeDictionary = "Việt - Anh";
                        searchWord = (String) VtoETable.getValueAt(VtoETable.getSelectedRow(), 0);
                        setEnabledSearchButtonandDeleteButton(true);
                    } else {
                        setEnabledSearchButtonandDeleteButton(false);  
                    }          
                }
            }
        });
    }

    public String getSearchWord() {
        return searchWord;
    }
    public String getTypeDictionary() {
        return typeDictionary;
    }
}
