package Controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerVtoE;
import View.AddWordPage;

public class AddWordPageController {
    private AddWordPage addWordPage;

    public AddWordPageController(AddWordPage addWordPage) {
        this.addWordPage = addWordPage;
        this.addWordPage.addAddWordButtonListener(new AddButtonListener());
    }
    
    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String typeDictionary = addWordPage.getTypeDictionary();
            String word = addWordPage.getWord();
            String meaning = addWordPage.getMeaning();

            if (typeDictionary.equals("Anh - Việt")) {
                ManagerEtoV managerEtoV = new ManagerEtoV();
                if (managerEtoV.addWordEtoV(word, meaning)) {
                    JOptionPane.showMessageDialog(null, "Thêm từ mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Từ đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                ManagerVtoE managerEVtoE = new ManagerVtoE();
                if (managerEVtoE.addWordVtoE(word, meaning)) {
                    JOptionPane.showMessageDialog(null, "Thêm từ mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Từ đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
