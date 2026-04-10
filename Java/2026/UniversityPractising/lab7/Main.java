import java.io.*;
import java.util.Scanner;

//Stwórz program przetwarzający linia po linii numeryczne dane wejściowe z pliku input.txt i obsługujący wskazane na
//diagramie UML wyjątki. Metoda processLine(String line, int lineNumber) powinna deklarować rzucanie wyjątków wszystkich podklas klasy
//InvalidDataException
//Poprawne przetworzenie pliku wejściowego powinno skutkować plikiem wyjściowym output.txt.
//Do operacji plikowych należy użyć konstrukcji "try z zasobami".
//
//Wskazówki:
//1. Problemy z konwersją tekstu na int można wykryć przechwytując wyjątek NumberFormatException deklarowany przez metodę parseInt
//2. Do wykrycia danych, których nie da się skonwertować do typu numerycznego użyj wyrażenia regularnego: line.matches(".*\\D.*")
//3. Do kolekcjonowania informacji o statusie odczytu poszczególnych linii można użyć instancji klasy StringBuilder. Wynik działania
// StringBuildera można użyć jako parametru w metodzie WriteSummaryToFile.

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        FileProcessor fileProcessor = new FileProcessor(inputFilePath, outputFilePath);
        fileProcessor.processFiles();
    }
}
