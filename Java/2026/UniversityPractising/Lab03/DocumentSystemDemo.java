import java.io.IOException;

//Zaprogramuj menadżer dokumentów uwaględniając następujące elementy:
//
//Dziedziczenie i polimorfizm:
//Interfejs Document
//Abstrakcyjna klasa BaseDocument
//Klasa potomna TextDocument
//
//Wzorce projektowe:
//Singleton:
//Klasa DocumentManager z metodą getInstance()
//
//Adapter:
//Klasa FileDocumentAdapter umożliwiająca odczyt dokumentów z pliku
//Uwaga: konstruktor FileDocumentAdapter powinien deklarować rzucanie wyjątku IOException
//        (sprawdź to zmieniając na chwilę nazwę odczytywanego pliku)
//
//
//Odczyt plików:
//Z użyciem java.io.FileReader i java.util.Scanner do parsowania zawartości pliku
//
//
//Obsługa wyjątków:
//Mechanizm try-catch
//Własna obsługa wyjątków IOException
//
//Do wygenerowania outputu wykorzystaj plik document.txt.

// Główna klasa demonstracyjna
public class DocumentSystemDemo {
    public static void main(String[] args) {
        try {
            // Tworzenie dokumentów
            Document textDoc1 = new TextDocument("Raport dzienny", "Dzisiejsze zadania zostały wykonane.");
            Document textDoc2 = new TextDocument("Raport dzienny", "Dzisiaj zmarnowałem cały dzień.");
            // Adapter do wczytania dokumentu z pliku
            Document fileDoc = new FileDocumentAdapter("document.txt");

            // Singleton do zarządzania dokumentami
            DocumentManager manager = DocumentManager.getInstance();
            manager.addDocument(textDoc1);
            manager.addDocument(textDoc2);
            manager.addDocument(fileDoc);

            // Wyświetlenie wszystkich dokumentów
            manager.displayAllDocuments();

        } catch (IOException e) {
            // Obsługa wyjątku związanego z odczytem pliku
            System.err.println("Wystąpił błąd: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

//Lista dokumentów:
//Dokument: Raport dzienny
//Treść: Dzisiejsze zadania zostały wykonane.
//---
//Dokument: Raport dzienny
//Treść: Dzisiaj zmarnowałem cały dzień.
//---
//Dokument: Dokument z pliku
//Treść: Pierwsza linia treści dokumentu.
//Druga linia treści dokumentu.
//
//---
