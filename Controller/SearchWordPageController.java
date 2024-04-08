package Controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerVtoE;
import View.SearchWordPage;

public class SearchWordPageController {
    private SearchWordPage searchWordPage;
    
    SearchWordPageController(SearchWordPage searchWordPage) {
        this.searchWordPage = searchWordPage;
        this.searchWordPage.addFavoriteButtonListener(new FavoriteButtonListener());
    }

    class FavoriteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String word = searchWordPage.getWord();
            String typeDictionary = searchWordPage.getTypeDictionary();

            if (typeDictionary.equals("Anh - Việt")) {
                ManagerEtoV managerEtoV = new ManagerEtoV();
                if (managerEtoV.addFavoriteWordEtoV(word)) {
                    JOptionPane.showMessageDialog(null, "Thêm từ yêu  thích thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    searchWordPage.setEnableButton(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm từ yêu thích thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                ManagerVtoE managerVtoE = new ManagerVtoE();
                if (managerVtoE.addFavoriteWordVtoE(word)) {
                    JOptionPane.showMessageDialog(null, "Thêm từ yêu thích thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    searchWordPage.setEnableButton(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm từ yêu thích thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
