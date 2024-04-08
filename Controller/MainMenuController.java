package Controller;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

import ManagerStorage.ManagerEtoV;
import ManagerStorage.ManagerHistory;
import ManagerStorage.ManagerVtoE;
import Model.Record.Meaning;
import View.AddWordPage;
import View.DeleteWordPage;
import View.FavoriteWordPage;
import View.HistoryPage;
import View.MainMenu;
import View.SearchWordPage;

public class MainMenuController {
    private MainMenu mainMenu;

    public MainMenuController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        this.mainMenu.addSearchButtonListener(new SearchButtonListener());
        this.mainMenu.addAddButtonListener(new AddWordButtonListener());
        this.mainMenu.addRemoveButtonListener(new RemoveWordButtonListener());
        this.mainMenu.addFavoriteButtonListener(new FavoriteWordButtonListener());
        this.mainMenu.addHistoryButtonListener(new HistoryButtonListener());
    }
    
    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (mainMenu.getSelectBoxString() == "Anh - Việt") {
                ManagerEtoV readEtoV = new ManagerEtoV();
                Meaning meaning = readEtoV.searchWordEtoV(mainMenu.getEnterField());
                if (meaning != null) {
                    SearchWordPage searchWordPage = new SearchWordPage(mainMenu.getEnterField(), meaning, "Anh - Việt");
                    @SuppressWarnings("unused")
                    SearchWordPageController searchWordPageController = new SearchWordPageController(searchWordPage);
                    ManagerHistory managerHistory = new ManagerHistory();
                    managerHistory.addHistory(mainMenu.getEnterField(), "Anh - Việt", LocalDate.now());
                } else {
                    JOptionPane.showMessageDialog(null, "Không có từ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                ManagerVtoE readVtoE = new ManagerVtoE();
                Meaning meaning = readVtoE.searchWordVtoE(mainMenu.getEnterField());
                if (meaning != null) {
                    SearchWordPage searchWordPage = new SearchWordPage(mainMenu.getEnterField(), meaning, "Việt - Anh");
                    @SuppressWarnings("unused")
                    SearchWordPageController searchWordPageController = new SearchWordPageController(searchWordPage);
                    ManagerHistory managerHistory = new ManagerHistory();
                    managerHistory.addHistory(mainMenu.getEnterField(), "Việt - Anh", LocalDate.now());
                } else {
                    JOptionPane.showMessageDialog(null, "Không có từ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
         
            }
        }
    }

    class AddWordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddWordPage addWordPage = new AddWordPage();
            mainMenu.setEnablePage(false);
            @SuppressWarnings("unused")
            AddWordPageController addWordPageController = new AddWordPageController(addWordPage);

            addWordPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mainMenu.setEnablePage(true);
                }
            });
        }
    }

    class RemoveWordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteWordPage deleteWordPage = new DeleteWordPage();
            mainMenu.setEnablePage(false);
            @SuppressWarnings("unused")
            DeleteWordPageController deleteWordPageController = new DeleteWordPageController(deleteWordPage);

            deleteWordPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mainMenu.setEnablePage(true);
                }
            });
        }
    }

    class FavoriteWordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FavoriteWordPage favoriteWordPage = new FavoriteWordPage();
            mainMenu.setEnablePage(false);
            
            ManagerEtoV managerEtoV = new ManagerEtoV();
            ManagerVtoE managerVtoE = new ManagerVtoE();

            List<String> favoriteWordsEtoV = managerEtoV.getFavoriteWordEtoV();
            List<String> favoriteWordsVtoE = managerVtoE.getFavoriteWordVtoE();

            favoriteWordPage.showFavoriteList(favoriteWordsEtoV, favoriteWordsVtoE);
            @SuppressWarnings("unused")
            FavoriteWordPageCotroller favoriteWordPageCotroller = new FavoriteWordPageCotroller(favoriteWordPage);

            favoriteWordPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mainMenu.setEnablePage(true);
                }
            });
        }
    }

    class HistoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HistoryPage historyPage = new HistoryPage();
            mainMenu.setEnablePage(false);

            @SuppressWarnings("unused")
            HistoryPageController historyPageController = new HistoryPageController(historyPage);
            historyPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mainMenu.setEnablePage(true);
                }
            });
        }
    }
}
