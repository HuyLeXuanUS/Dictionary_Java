package ManagerStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.Dictionary;
import Model.Record.Meaning;
import Model.Record.Word;

public class ManagerVtoE {
    private static final String FILE_NAME = "Viet_Anh.xml";
    private static final String filePath = "StorageXML/" + FILE_NAME;

    public static Dictionary<String, Meaning> readFileVtoExml () throws SAXException, IOException {
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Dictionary<String, Meaning> dictionaryVtoE = new Dictionary<>();

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            Element dictionary = doc.getDocumentElement();
            NodeList listWord = dictionary.getElementsByTagName("record");

            for (int i = 0; i < listWord.getLength(); i++) {
                Node word = listWord.item(i);
                if (word.getNodeType() == Node.ELEMENT_NODE) {
                    Element wordElement = (Element) word;
                    String wordName = wordElement.getElementsByTagName("word").item(0).getTextContent();
                    String meaning = wordElement.getElementsByTagName("meaning").item(0).getTextContent();

                    Meaning meanningVtoE = new Meaning();
                    meanningVtoE.getMeaningVtoE(meaning, wordName);
                    dictionaryVtoE.addEntry(wordName, meanningVtoE);
                }
            }
            return dictionaryVtoE;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }     
    }

    public static void exportDictionaryToFile(Dictionary<String, Meaning> dictionary) {
        Map<Character, List<Word>> map = new HashMap<>();
        for (Map.Entry<String, Meaning> entry : dictionary.getEntries().entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue());
            char firstLetter = word.getWord().charAt(0);
            map.computeIfAbsent(firstLetter, k -> new ArrayList<>()).add(word);
        }

        for (char c : map.keySet()) {
            List<Word> words = map.get(c);
            FileOutputStream fileOut;
            ObjectOutputStream objectOut;

            String filePathSave = "StorageVtoE/" + c + ".dat";
            try {
                fileOut = new FileOutputStream(new File(filePathSave));
                objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(words);
                objectOut.close();
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static Dictionary<String, Meaning> readListWordFromFile(char c) {
        List<Word> studentList = new ArrayList<Word>();
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        String readFilePath = "StorageVtoE/" + c + ".dat";
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        try {
            fileIn = new FileInputStream(new File(readFilePath));
            objectIn = new ObjectInputStream(fileIn);
            studentList = (List<Word>)objectIn.readObject();
            for (Word word : studentList) {
                dictionary.addEntry(word.getWord(), word.getMeaning());
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static void writeListWordToFile(char c, List<Word> words) {
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        String filePathSave = "StorageVtoE/" + c + ".dat";
        try {
            fileOut = new FileOutputStream(new File(filePathSave));
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(words);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Meaning searchWordVtoE(String word) {
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        dictionary = readListWordFromFile(word.charAt(0));
        return dictionary.findEntry(word);
    }

    public boolean addWordVtoE(String word, String meaning) {
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        dictionary = readListWordFromFile(word.charAt(0));

        if (dictionary.findEntry(word) != null) {
            return false;
        } else {
            Meaning meaningVtoE = new Meaning(meaning);
            dictionary.addEntry(word, meaningVtoE);
            List<Word> words = new ArrayList<>();
            for (Map.Entry<String, Meaning> entry : dictionary.getEntries().entrySet()) {
                Word temp = new Word(entry.getKey(), entry.getValue());
                words.add(temp);
            }
            writeListWordToFile(word.charAt(0), words);
        }
        return true;
    }

    public boolean deleteWordVtoE(String word) {
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        dictionary = readListWordFromFile(word.charAt(0));

        if (dictionary.findEntry(word) == null) {
            return false;
        } else {
            dictionary.removeEntry(word);
            List<Word> words = new ArrayList<>();
            for (Map.Entry<String, Meaning> entry : dictionary.getEntries().entrySet()) {
                Word temp = new Word(entry.getKey(), entry.getValue());
                words.add(temp);
            }
            writeListWordToFile(word.charAt(0), words);
        }
        return true;
    }

    // Favorite word
    @SuppressWarnings({ "unchecked", "resource" })
    public List<String> getFavoriteWordVtoE() {
        List<String> favoriteWords = new ArrayList<>();
        String readFilePath = "StorageVtoE/favorite.dat";
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        if (!new File(readFilePath).exists()) {
            return favoriteWords;
        }

        try {
            fileIn = new FileInputStream(new File(readFilePath));
            objectIn = new ObjectInputStream(fileIn);
            favoriteWords = (List<String>)objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return favoriteWords;
    }

    public static void writeFavoriteWordVtoE(List<String> favoriteWords) {
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        String filePathSave = "StorageVtoE/favorite.dat";
        try {
            fileOut = new FileOutputStream(new File(filePathSave));
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(favoriteWords);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addFavoriteWordVtoE(String word) {
        List<String> favoriteWords = getFavoriteWordVtoE();
        if (favoriteWords.contains(word)) {
            return false;
        } else 
        favoriteWords.add(word);
        writeFavoriteWordVtoE(favoriteWords);
        return true;
    }

    public boolean removeFavoriteWordVtoE(String word) {
        List<String> favoriteWords = getFavoriteWordVtoE();
        if (!favoriteWords.contains(word)) {
            return false;
        }
        favoriteWords.remove(word);
        writeFavoriteWordVtoE(favoriteWords);
        return true;
    }

    public boolean isFavoriteWord(String word) {
        List<String> favoriteWords = getFavoriteWordVtoE();
        if (favoriteWords == null) {
            return false;
        }
        return favoriteWords.contains(word);
    }

    public static void main(String[] args) {
        try {
            Dictionary<String, Meaning> dictionary = readFileVtoExml();
            exportDictionaryToFile(dictionary);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
