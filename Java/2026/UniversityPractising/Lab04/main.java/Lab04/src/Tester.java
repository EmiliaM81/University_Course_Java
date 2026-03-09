/**
 * Zadanie: System porównywania i zarządzania
 *
 * Stwórz zestaw klas i zawartych w nich metod statycznych, który demonstruje operacje na obiektach,
 * z uwzględnieniem klonowania, metod klasy String, metod equals, hashCode,...odziedziczonych z klasy Object
 * oraz refleksji.
 */

public class Tester {
    public static void main(String[] args) {
        // Tworzymy różne obiekty tekstowe
        TextEntity text1 = new TextEntity("Przykładowy tekst", "pl", 1);

        CloneTester.testCloning(text1);

        System.out.println("\n--- Operacje na String API ---");
        StringOperationTester.stringOperations(text1.getContent());

        TextEntity text2 = new TextEntity("Przykładowy tekst", "pl", 2);
        TextEntity text3 = new TextEntity("Example text", "en", 3);
        System.out.println("\n--- Porównanie obiektów ---");
        ObjectComparisonTester.compareObjects(text1, text2, text3);

        System.out.println("\n--- Refleksja i instanceof ---");
        ObjectReflectionAnalyser.analyzeObject(text1);
        ObjectReflectionAnalyser.analyzeObject("To jest zwykły String");
    }


}

/*
 * Output:

Oryginalny obiekt: TextEntity{id=1, language='pl', content='Przykładowy tekst'}
Sklonowany obiekt: TextEntity{id=1, language='pl', content='Przykładowy tekst'}
Czy to ten sam obiekt? false
Czy obiekty są równe? true
HashCode oryginalnego: 657230859
HashCode sklonowanego: 657230859

--- Operacje na String API ---
Oryginalny tekst: Przykładowy tekst
Długość tekstu: 17
Tekst wielkimi literami: PRZYKŁADOWY TEKST
Tekst małymi literami: przykładowy tekst
Indeks pierwszego 'a': 6
Podciąg od 5 do 10: ładow
Czy zaczyna się od 'Przyk': true
Podzielono na 2 słowa:
 - Przykładowy
 - tekst

--- Porównanie obiektów ---
t1.equals(t2): true
t1.equals(t3): false
t1.hashCode(): 657230859
t2.hashCode(): 657230859
t3.hashCode(): 527796934
Rozmiar HashSet: 2
Zawartość HashSet: [TextEntity{id=3, language='en', content='Example text'}, TextEntity{id=1, language='pl', content='Przykładowy tekst'}]

--- Refleksja i instanceof ---

Analizuję obiekt: TextEntity{id=1, language='pl', content='Przykładowy tekst'}
Nazwa klasy: TextEntity
Klasa nadrzędna: java.lang.Object
Czy to interfejs? false
Dostępne metody:
 - equals
 - toString
 - hashCode
 - ... i 2 więcej metod
Czy to TextEntity? true
Czy to String? false
Czy to Cloneable? true
Czy to Comparable? false

Analizuję obiekt: To jest zwykły String
Nazwa klasy: java.lang.String
Klasa nadrzędna: java.lang.Object
Czy to interfejs? false
Dostępne metody:
 - value
 - equals
 - length
 - ... i 148 więcej metod
Czy to TextEntity? false
Czy to String? true
Czy to Cloneable? false
Czy to Comparable? true
 */