package ManagerStorage;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import Model.Dictionary;
import Model.Record.Meaning;
import Model.Record.Word;

public class ManagerEtoV {
    private static final String FILE_NAME = "Anh_Viet.xml";
    private static final String filePath = "StorageXML/" + FILE_NAME;

    public static Dictionary<String, Meaning> readFileEtoVxml () throws SAXException, IOException {
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Dictionary<String, Meaning> dictionaryEtoV = new Dictionary<>();
        
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

                    Meaning meaningEtoV = new Meaning();
                    meaningEtoV.getMeaningEtoV(meaning, wordName);
                    dictionaryEtoV.addEntry(wordName, meaningEtoV);
                }
            }
            return dictionaryEtoV;
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

            String filePathSave = "StorageEtoV/" + c + ".dat";
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
        String readFilePath = "StorageEtoV/" + c + ".dat";
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

        String filePathSave = "StorageEtoV/" + c + ".dat";
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

    public Meaning searchWordEtoV(String word) {
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        dictionary = readListWordFromFile(word.charAt(0));
        return dictionary.findEntry(word);
    }

    public boolean addWordEtoV(String word, String meaning) {
        Dictionary<String, Meaning> dictionary = new Dictionary<>();
        dictionary = readListWordFromFile(word.charAt(0));

        if (dictionary.findEntry(word) != null) {
            return false;
        } else {
            Meaning meaningEtoV = new Meaning(meaning);
            dictionary.addEntry(word, meaningEtoV);
            List<Word> words = new ArrayList<>();
            for (Map.Entry<String, Meaning> entry : dictionary.getEntries().entrySet()) {
                Word temp = new Word(entry.getKey(), entry.getValue());
                words.add(temp);
            }
            writeListWordToFile(word.charAt(0), words);
        }
        return true;
    }

    public boolean deleteWordEtoV(String word) {
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

    // Favorite Word
    @SuppressWarnings({ "unchecked", "resource" })
    public List<String> getFavoriteWordEtoV() {
        List<String> favoriteWords = new ArrayList<>();
        String readFilePath = "StorageEtoV/favorite.dat";
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

    public static void writeFavoriteWordEtoV(List<String> favoriteWords) {
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        String filePathSave = "StorageEtoV/favorite.dat";
        try {
            fileOut = new FileOutputStream(new File(filePathSave));
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(favoriteWords);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addFavoriteWordEtoV(String word) {
        List<String> favoriteWords = getFavoriteWordEtoV();
        if (favoriteWords.contains(word)) {
            return false;
        }
        favoriteWords.add(word);
        writeFavoriteWordEtoV(favoriteWords);
        return true;
    }

    public boolean removeFavoriteWordEtoV(String word) {
        List<String> favoriteWords = getFavoriteWordEtoV();
        if (!favoriteWords.contains(word)) {
            return false;
        }
        favoriteWords.remove(word);
        writeFavoriteWordEtoV(favoriteWords);
        return true;
    }

    public boolean isFavoriteWord(String word) {
        List<String> favoriteWords = getFavoriteWordEtoV();
        if (favoriteWords == null) {
            return false;
        }
        return favoriteWords.contains(word);
    }

    public static void main(String[] args) {
        try {
            Dictionary<String, Meaning> dictionary = readFileEtoVxml();
            exportDictionaryToFile(dictionary);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}