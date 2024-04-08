package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerHistory;
import ManagerStorage.ManagerVtoE;
import Model.Record.Meaning;
import View.FavoriteWordPage;
import View.SearchWordPage;

public class FavoriteWordPageCotroller {
    private List<String> favoriteWordsEtoV;
    private List<String> favoriteWordsVtoE;

    private FavoriteWordPage favoriteWordPage;

    public FavoriteWordPageCotroller(FavoriteWordPage favoriteWordPage) {
        this.favoriteWordPage = favoriteWordPage;
        ManagerEtoV managerEtoV = new ManagerEtoV();
        ManagerVtoE managerVtoE = new ManagerVtoE();

        this.favoriteWordsEtoV = managerEtoV.getFavoriteWordEtoV();
        this.favoriteWordsVtoE = managerVtoE.getFavoriteWordVtoE();

        this.favoriteWordPage.addAtoZButtonListener(new AtoZButtonListener());
        this.favoriteWordPage.addZtoAButtonListener(new ZtoAButtonListener());
        this.favoriteWordPage.addSearchButtonListener(new SearchButtonListener());
        this.favoriteWordPage.addDeleteButtonListener(new DeleteButtonListener());
    }

    class AtoZButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(favoriteWordsEtoV);
            Collections.sort(favoriteWordsVtoE);
            favoriteWordPage.showFavoriteList(favoriteWordsEtoV, favoriteWordsVtoE);
        }
    }

    class ZtoAButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(favoriteWordsEtoV, Collections.reverseOrder());
            Collections.sort(favoriteWordsVtoE, Collections.reverseOrder());
            favoriteWordPage.showFavoriteList(favoriteWordsEtoV, favoriteWordsVtoE);
        }
    }

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String word = favoriteWordPage.getSearchWord();
            String typeDictionary = favoriteWordPage.getTypeDictionary();

            if (typeDictionary == "Anh - Việt") {
                ManagerEtoV readEtoV = new ManagerEtoV();
                Meaning meaning = readEtoV.searchWordEtoV(word);
                if (meaning != null) {
                    SearchWordPage searchWordPage = new SearchWordPage(word, meaning, "Anh - Việt");
                    @SuppressWarnings("unused")
                    SearchWordPageController searchWordPageController = new SearchWordPageController(searchWordPage);
                    ManagerHistory managerHistory = new ManagerHistory();
                    managerHistory.addHistory(word, "Anh - Việt", LocalDate.now());
                } else {
                    JOptionPane.showMessageDialog(null, "Không có từ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                ManagerVtoE readVtoE = new ManagerVtoE();
                Meaning meaning = readVtoE.searchWordVtoE(word);
                if (meaning != null) {
                    SearchWordPage searchWordPage = new SearchWordPage(word, meaning, "Việt - Anh");
                    @SuppressWarnings("unused")
                    SearchWordPageController searchWordPageController = new SearchWordPageController(searchWordPage);
                    ManagerHistory managerHistory = new ManagerHistory();
                    managerHistory.addHistory(word, "Việt - Anh", LocalDate.now());
                } else {
                    JOptionPane.showMessageDialog(null, "Không có từ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String word = favoriteWordPage.getSearchWord();
            String typeDictionary = favoriteWordPage.getTypeDictionary();

            if (typeDictionary == "Anh - Việt") {
                ManagerEtoV managerEtoV = new ManagerEtoV();
                managerEtoV.removeFavoriteWordEtoV(word);
                favoriteWordsEtoV = managerEtoV.getFavoriteWordEtoV();
                favoriteWordPage.showFavoriteList(favoriteWordsEtoV, favoriteWordsVtoE);
            } else {
                ManagerVtoE managerVtoE = new ManagerVtoE();
                managerVtoE.removeFavoriteWordVtoE(word);
                favoriteWordsVtoE = managerVtoE.getFavoriteWordVtoE();
                favoriteWordPage.showFavoriteList(favoriteWordsEtoV, favoriteWordsVtoE);
            }
        }
    }
    
}
