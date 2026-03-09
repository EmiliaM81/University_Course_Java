/**
 * Zadanie: System zarządzania sklepem muzycznym
 * <p>
 * Celem zadania jest implementacja systemu do zarządzania asortymentem
 * sklepu muzycznego z wykorzystaniem dziedziczenia, polimorfizmu,
 * klas abstrakcyjnych i interfejsów.
 */
//Wskazówki:
//Użyto następujących algorytmów:
//Koszt wypożyczenia instrumentu:
//public double obliczKosztWypozyczenia(int liczbaDni) {
//    // Zniżka przy dłuższym okresie wypożyczenia
//    if (liczbaDni > 30) {
//        return liczbaDni * dziennaStawka * 0.8; // 20% zniżki
//    } else if (liczbaDni > 7) {
//        return liczbaDni * dziennaStawka * 0.9; // 10% zniżki
//    }
//    return liczbaDni * dziennaStawka;
//}
//
//Ceny płyt:
//public double obliczCene() {
//    // Nowsze płyty (ostatnie 5 lat) są droższe o 10%
//    int aktualnyRok = 2025;
//    return (aktualnyRok - rokWydania <= 5) ? cenaBazowa * 1.1 : cenaBazowa;
//}

//Ceny akcesoriów:
//public double obliczCene() {
//    // Profesjonalne akcesoria są droższe o 25%
//    return czyProfesjonalne ? cenaBazowa * 1.25 : cenaBazowa;
//}

//Ceny instrumentów:
//public double obliczCene() {
//    // Instrumenty akustyczne są droższe o 15%
//    return czyAkustyczny ? cenaBazowa * 1.15 : cenaBazowa;
//}

//Prawidłowy output umieszczono na dole klasy.

// Klasa demonstrująca użycie systemu
public class SklepMuzycznyDemo {
    public static void main(String[] args) {
        // Utworzenie sklepu
        SklepMuzyczny sklep = new SklepMuzyczny(10);

        // Dodanie produktów
        sklep.dodajProdukt(new Instrument("Gitara Klasyczna", 1200.0, 5,
                "Gitara", "Yamaha", true));

        sklep.dodajProdukt(new Instrument("Pianino Elektryczne", 3500.0, 2,
                "Pianino", "Roland", false));

        sklep.dodajProdukt(new Plyta("The Dark Side of the Moon", 59.99, 15,
                "Pink Floyd", 1973, "Rock"));

        sklep.dodajProdukt(new Plyta("Future Nostalgia", 49.99, 10,
                "Dua Lipa", 2020, "Pop"));

        sklep.dodajProdukt(new Akcesorium("Struny do gitary elektrycznej", 45.0, 20,
                "Struny", false));

        InstrumentDoWyporzyczenia gitaraDoWypozyczenia = new InstrumentDoWyporzyczenia(
                "Gitara Elektryczna", 2500.0, 3, "Gitara", "Fender", false, 50.0);
        sklep.dodajProdukt(gitaraDoWypozyczenia);

        // Wyświetlenie wszystkich produktów
        System.out.println("Asortyment sklepu muzycznego:");
        sklep.wyswietlWszystkieProdukty();

        // Sprzedaż produktu
        System.out.println("\nSprzedaż produktu:");
        sklep.sprzedajProdukt(0, 2);

        // Wypożyczenie instrumentu
        System.out.println("\nWypożyczenie instrumentu:");
        sklep.wypozyczInstrument(gitaraDoWypozyczenia, 14);

        // Próba ponownego wypożyczenia tego samego instrumentu
        System.out.println("\nPróba ponownego wypożyczenia:");
        sklep.wypozyczInstrument(gitaraDoWypozyczenia, 7);

        // Zwrot instrumentu
        System.out.println("\nZwrot instrumentu:");
        sklep.zwrocInstrument(gitaraDoWypozyczenia);

        // Wyświetlenie stanu magazynu po operacjach
        System.out.println("\nAktualny stan magazynu:");
        sklep.wyswietlWszystkieProdukty();
    }
}

//Asortyment sklepu muzycznego:
//Instrument: Gitara Klasyczna, Marka: Yamaha, Rodzaj: Gitara, akustyczny, Cena: 1380,00 zł
//Instrument: Pianino Elektryczne, Marka: Roland, Rodzaj: Pianino, elektryczny, Cena: 3500,00 zł
//Płyta: The Dark Side of the Moon, Wykonawca: Pink Floyd, Rok: 1973, Gatunek: Rock, Cena: 59,99 zł
//Płyta: Future Nostalgia, Wykonawca: Dua Lipa, Rok: 2020, Gatunek: Pop, Cena: 54,99 zł
//Akcesorium: Struny do gitary elektrycznej, Kategoria: Struny, amatorskie, Cena: 45,00 zł
//Instrument: Gitara Elektryczna, Marka: Fender, Rodzaj: Gitara, elektryczny, Cena: 2500,00 zł, Stawka dzienna: 50,00 zł, Dostępny
//
//Sprzedaż produktu:
//Sprzedano: Instrument: Gitara Klasyczna, Marka: Yamaha, Rodzaj: Gitara, akustyczny, Cena: 1380,00 zł
//
//Wypożyczenie instrumentu:
//Wypożyczono: Instrument: Gitara Elektryczna, Marka: Fender, Rodzaj: Gitara, elektryczny, Cena: 2500,00 zł, Stawka dzienna: 50,00 zł, Niedostępny
//Koszt wypożyczenia na 14 dni: 630.0 zł
//
//Próba ponownego wypożyczenia:
//Błąd: Instrument nie jest dostępny do wypożyczenia
//
//Zwrot instrumentu:
//Zwrócono: Instrument: Gitara Elektryczna, Marka: Fender, Rodzaj: Gitara, elektryczny, Cena: 2500,00 zł, Stawka dzienna: 50,00 zł, Dostępny
//
//Aktualny stan magazynu:
//Instrument: Gitara Klasyczna, Marka: Yamaha, Rodzaj: Gitara, akustyczny, Cena: 1380,00 zł
//Instrument: Pianino Elektryczne, Marka: Roland, Rodzaj: Pianino, elektryczny, Cena: 3500,00 zł
//Płyta: The Dark Side of the Moon, Wykonawca: Pink Floyd, Rok: 1973, Gatunek: Rock, Cena: 59,99 zł
//Płyta: Future Nostalgia, Wykonawca: Dua Lipa, Rok: 2020, Gatunek: Pop, Cena: 54,99 zł
//Akcesorium: Struny do gitary elektrycznej, Kategoria: Struny, amatorskie, Cena: 45,00 zł
//Instrument: Gitara Elektryczna, Marka: Fender, Rodzaj: Gitara, elektryczny, Cena: 2500,00 zł, Stawka dzienna: 50,00 zł, Dostępny
