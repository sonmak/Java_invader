package Test;

import Client.Constants;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static int GameFieldWidth = 0;
    public static int GameFieldHeight = 0;

    public static String rules;
    private int highScore;

    private static Constants c = new Constants();

    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public static void cls() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public static void testConfig() {
        try {
            File xmlInputFile = new File(c.field_config);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlInputFile);
            doc.getDocumentElement().normalize();

            GameFieldWidth = Integer.parseInt(doc.getElementsByTagName("GameFieldWidth").item(0).getTextContent());
            GameFieldHeight = Integer.parseInt(doc.getElementsByTagName("GameFieldHeight").item(0).getTextContent());
            int Timer = Integer.parseInt(doc.getElementsByTagName("Timer").item(0).getTextContent());
            int LevelAmount = Integer.parseInt(doc.getElementsByTagName("LevelAmount").item(0).getTextContent());
            String StartBTNtext = doc.getElementsByTagName("StartBTNtext").item(0).getTextContent();
            String EndBTNtext = doc.getElementsByTagName("EndBTNtext").item(0).getTextContent();

            //wczytanie pliku z zasadami
            xmlInputFile = new File(c.rules_config);
            doc = dBuilder.parse(xmlInputFile);
            doc.getDocumentElement().normalize();

            String rules = doc.getElementsByTagName("rules").item(0).getTextContent();

            //wczytanie pliku z poziomami
            LevelAmount = 3;
            Map<Integer, Integer> buff = new HashMap<Integer, Integer>();
            xmlInputFile = new File(c.levels_speed);
            doc = dBuilder.parse(xmlInputFile);
            doc.getDocumentElement().normalize();

            for (int i = 0; i < LevelAmount; i++) {
                int buff_int = Integer.parseInt(doc.getElementsByTagName("level_" + (i + 1)).item(0).getTextContent());
                buff.put(i, buff_int);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testMap(int level) {
        try {
            char[][] field = new char[20][19];
            BufferedReader bufferreader = new BufferedReader(new FileReader(c.maps + "level_" + level + ".txt"));
            String line;
            int line_counter = 0;
            while ((line = bufferreader.readLine()) != null) {
                if (line.length() != 19) {
                    throw new Exception("wrong table width");
                }
                for (int i = 0; i < 19; i++) {
                    field[line_counter][i] = line.charAt(i);
                }
                line_counter++;
            }
            if (line_counter != 20) throw new Exception("wrong line amount");
            System.out.println("Map of chosen level:");
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 19; j++) {
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
