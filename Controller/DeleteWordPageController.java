package Controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerVtoE;
import View.DeleteWordPage;

public class DeleteWordPageController {
    private DeleteWordPage deleteWordPage;

    public DeleteWordPageController(DeleteWordPage deleteWordPage) {
        this.deleteWordPage = deleteWordPage;
        this.deleteWordPage.addDeleteWordButtonListener(new DeleteButtonListener());
    }
    
    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String typeDictionary = deleteWordPage.getTypeDictionary();
            String word = deleteWordPage.getWordField();

            if (typeDictionary.equals("Anh - Việt")) {
                ManagerEtoV managerEtoV = new ManagerEtoV();
                if (managerEtoV.deleteWordEtoV(word)) {
                    JOptionPane.showMessageDialog(null, "Xóa từ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Từ không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                ManagerVtoE managerEVtoE= new ManagerVtoE();
                if (managerEVtoE.deleteWordVtoE(word)) {
                    JOptionPane.showMessageDialog(null, "Xóa từ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Từ không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
