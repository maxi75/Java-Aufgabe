import java.io.*;
import java.util.ArrayList;

public class main {

    // starten der Anwendung
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> resultReadFile = new ArrayList<>();
        resultReadFile.add(new ArrayList<>());
        resultReadFile.add(new ArrayList<>());

        // Einlesen der Input-Datei
        resultReadFile = readFile("input.txt");

        // Erstellen der Output-Datei
        File file = new File("output.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        // Erstellung der Verschlüsselung und schreiben des verschlüsselten Strings in die Output-Datei
        for (int i = 0; i < resultReadFile.get(0).size(); i++){
            String s = chiffriere(resultReadFile.get(0).get(i), resultReadFile.get(1).get(i));
            System.out.println(s);
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileOutputStream.close();
    }

    // Die Methode chiffriere nimmt einen Klartext und einen mindestens genauso langen Schlüssel entgegen,
    // verschlüsselt dann den Klartext mit dem Schlüsselwort und gibt den verschlüsselten Satz zurück
    private static String chiffriere(String plaintext, String keyword){

        int firstLetter;
        int secondLetter;
        StringBuilder ciphertext = new StringBuilder();

        // Die for-Schleife geht durch jeden Buchstaben des Klartextes durch, nimmt das passende Zeichen aus dem
        // Schlüsselwort dazu und berechnet dann das verschlüsselte Zeichen daraus
        for (int i = 0; i < plaintext.length(); i++){
            firstLetter = plaintext.charAt(i);
            secondLetter = keyword.charAt(i);

            char resultChar = (char) ((firstLetter + secondLetter) % 256);
            ciphertext.append(resultChar);
        }

        return ciphertext.toString();
    }

    // readFile nimmt einen Pfad entgegen, öffnet die Datei, liest Zeile für Zeile ein und gibt eine Arraylist
    // mit Klartext und Schlüsselwort zurück.
    // Die Datei muss im Klartext#Schlüsselwort-Format vorliegen.
    private static ArrayList<ArrayList<String>> readFile(String path) throws IOException {
        ArrayList<ArrayList<String>> fileInput = new ArrayList<>();
        fileInput.add(new ArrayList<>());
        fileInput.add(new ArrayList<>());

        // öffnen der Datei
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        // Zeile für Zeile einlesen, wenn die Datei zu Ende ist, wird die Schleife abgebrochen, ansonsten werden
        // Klartext und Schlüsselwort in der ArrayList gespeichert
        String line;
        while (true){
            line = bufferedReader.readLine();
            if(line == null){break;}

            String[] result = line.split("#");

            fileInput.get(0).add(result[0]);
            fileInput.get(1).add(result[1]);

        }
        bufferedReader.close();

        return fileInput;
    }
}
