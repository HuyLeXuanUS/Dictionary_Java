package ManagerStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.History;
import Model.LookupHistory;

public class ManagerHistory {
    @SuppressWarnings("unchecked")
    private List<History> readHistory() {
        List<History> historyList = new ArrayList<>();
        String historyFilePath = "StorageHistory/history.dat";
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        if (!new File(historyFilePath).exists()) {
            return historyList;
        }
        try {
            fileIn = new FileInputStream(new File(historyFilePath));
            objectIn = new ObjectInputStream(fileIn);
            historyList = (List<History>)objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return historyList;
    }

    private static void writeHistory(List<History> historyList) {
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        String historyFilePath = "StorageHistory/history.dat";
        try {
            fileOut = new FileOutputStream(new File(historyFilePath));
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(historyList);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHistory(String word, String type, LocalDate date) {
        List<History> historyList = new ManagerHistory().readHistory();
        historyList.add(new History(word, type, date));
        writeHistory(historyList);
    }

    public List<LookupHistory> getHistory(LocalDate datefrom, LocalDate dateto) {
        List<History> historyList = new ManagerHistory().readHistory();
        List<LookupHistory> lookupHistoryList = new ArrayList<>();
        for (History history : historyList) {
            if (!history.getDate().isAfter(dateto) && !history.getDate().isBefore(datefrom)) {
                boolean found = false;

                for (LookupHistory lookupHistory : lookupHistoryList) {
                    if (lookupHistory.getWord().equals(history.getWord())
                    && lookupHistory.getType().equals(history.getType())) {
                        lookupHistory.setCount(lookupHistory.getCount() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    lookupHistoryList.add(new LookupHistory(history.getWord(), history.getType(), 1));
                }             
            }
        }
        return lookupHistoryList;
    }

}
