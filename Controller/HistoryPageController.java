package Controller;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;

import ManagerStorage.ManagerHistory;
import Model.LookupHistory;
import View.HistoryPage;

public class HistoryPageController {
    private HistoryPage historyPage;

    public HistoryPageController(HistoryPage historyPage) {
        this.historyPage = historyPage;
        this.historyPage.addSearchButtonListener(new SearchButtonListener());
    }

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dateFrom = historyPage.getDateFrom();
            String dateTo = historyPage.getDateTo();

            if (isValidDate(dateFrom) && isValidDate(dateTo)) {
                LocalDate dateFromLocal = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dateToLocal = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dateFromLocal.isAfter(dateToLocal)) {
                    JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    ManagerHistory managerHistory = new ManagerHistory();
                    List<LookupHistory> historyList = managerHistory.getHistory(dateFromLocal, dateToLocal);
                    historyPage.showHistory(historyList);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ngày tháng nhập không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private boolean isValidDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            @SuppressWarnings("unused")
            LocalDate date = LocalDate.parse(dateString, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
