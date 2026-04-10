import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class FileProcessor {
    private String inputFilePath;
    private String outputFilePath;
    private StringBuilder summary;

    public FileProcessor(String inputFilePath, String outputFilePath)
    {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.summary = new StringBuilder();
    }

    public void processFiles()
    {
        try 
        {
            processFile();
        } catch (EmptyFileException e) {
            summary.append("Error: ").append(e.getMessage()).append("\n");
        } finally {
            writeSummaryToFile(summary.toString());
        }
    }
    public String processFile() throws EmptyFileException {
        StringBuilder poprawneDane = new StringBuilder();
        int numerLinii = 1;
        
        // Try-with-resources do czytania pliku
        try (Scanner skaner = new Scanner(new File(inputFilePath))) {
            
            // Sprawdzenie czy plik jest pusty
            if (!skaner.hasNextLine()) {
                throw new EmptyFileException("Plik wejściowy jest pusty!");
            }
            
            // Czytanie linia po linii
            while (skaner.hasNextLine()) {
                String linia = skaner.nextLine();
                
                try {
                    // Przetwarzanie pojedynczej linii
                    int liczba = processLine(linia, numerLinii);
                    poprawneDane.append(liczba).append("\n");
                    
                } catch (InvalidDataException e) {
                    // Zapisanie błędu do podsumowania
                    summary.append("Linia ").append(numerLinii).append(": ").append(e.getMessage()).append("\n");
                }
                
                numerLinii++;
            }
            
            // Zapisanie poprawnych danych do pliku wyjściowego
            try (FileWriter pisarz = new FileWriter(outputFilePath)) {
                pisarz.write(poprawneDane.toString());
                System.out.println("Zapisano poprawne dane do pliku: " + outputFilePath);
            }
            
        } catch (FileNotFoundException e) {
            summary.append("Nie znaleziono pliku: ").append(inputFilePath).append("\n");
        } catch (IOException e) {
            summary.append("Błąd podczas zapisu: ").append(e.getMessage()).append("\n");
        }
        
        return poprawneDane.toString();
    }
    
    // Metoda przetwarzająca pojedynczą linię
    public int processLine(String line, int lineNumber) 
            throws EmptyLineException, NonNumericDataException, UnexpectedCharacterException {
        
        // Sprawdzenie czy linia nie jest pusta
        if (line == null || line.trim().isEmpty()) {
            throw new EmptyLineException("Linia jest pusta");
        }
        
        String trimmedLine = line.trim();
        
        // Sprawdzenie czy linia zawiera tylko cyfry (opcjonalnie ze znakiem minus)
        if (!trimmedLine.matches("-?\\d+")) {
            // Sprawdzenie czy są jakieś znaki specjalne
            if (trimmedLine.matches(".*[^0-9-].*")) {
                throw new UnexpectedCharacterException("Znaleziono niedozwolony znak");
            } else {
                throw new NonNumericDataException("Dane nie są liczbą");
            }
        }
        
        try {
            // Próba konwersji na liczbę
            return Integer.parseInt(trimmedLine);
        } catch (NumberFormatException e) {
            throw new NonNumericDataException("Nie można przekonwertować na liczbę");
        }
    }
    
    // Metoda zapisująca podsumowanie do pliku
    public void writeSummaryToFile(String summaryContent) {
        // Try-with-resources do zapisu pliku z podsumowaniem
        try (FileWriter pisarz = new FileWriter("summary.txt")) {
            pisarz.write(summaryContent);
            System.out.println("Zapisano podsumowanie do pliku: summary.txt");
            System.out.println("\nPODSUMOWANIE:");
            System.out.println(summaryContent);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu podsumowania: " + e.getMessage());
        }
    }
}



