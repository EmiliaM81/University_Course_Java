// Main.java
import java.util.*;
import java.nio.file.*;
import java.io.IOException;


//Odczytaj dane z pliku products.txt a następnie stwórz umieszczone poniżej zestawienia.
//W tym celu wykorzystaj:
//-Kolekcje
//-Wyrażenia lambda
//-Przetwarzanie strumieniowe (Stream API)
//-Referencje do metod
//UWAGA: Należy korzystać wyłącznie z przetwarzania strumieniowego. Nie wolno używać if-ów.

public class Main {
    public static void main(String[] args) {
        // Wczytanie produktów z pliku
        List<Product> products = loadProductsFromFile("products.txt");

        ProductProcessor processor = new ProductProcessor(products);

        // Sprawdź czy udało się wczytać dane
        if (products.isEmpty()) {
            System.out.println("Nie udało się wczytać produktów z pliku. Program zostanie zakończony.");
            return;
        }

        // ProductProcessor processor = new ProductProcessor(products);

        System.out.println("========== LISTA WSZYSTKICH PRODUKTÓW ==========");
        products.forEach(System.out::println);

        System.out.println("\n========== GRUPOWANIE WEDŁUG KATEGORII ==========");
        Map<String, List<Product>> groupedByCategory = processor.groupByCategory();
        groupedByCategory.forEach((category, productList) -> {
            System.out.println("\nKategoria: " + category);
            productList.forEach(product -> System.out.println("  - " + product));
        });

        System.out.println("\n========== ŚREDNIA CENA W KATEGORIACH ==========");
        Map<String, Double> avgPriceByCategory = processor.calculateAveragePriceByCategory();
        avgPriceByCategory.forEach((category, avgPrice) ->
                System.out.printf("%s: %.2f zł\n", category, avgPrice));

        System.out.println("\n========== NAJDROŻSZE PRODUKTY W KATEGORIACH ==========");
        Map<String, Product> mostExpensive = processor.findMostExpensiveByCategory();
        mostExpensive.forEach((category, product) ->
                System.out.printf("%s: %s (%.2f zł)\n", category, product.getName(), product.getPrice()));

        System.out.println("\n========== PRODUKTY Z CENĄ POWYŻEJ ŚREDNIEJ ==========");
        List<Product> aboveAverage = processor.filterProductsAboveAveragePrice();
        aboveAverage.forEach(System.out::println);

        System.out.println("\n========== PRODUKTY POSORTOWANE WEDŁUG CENY MALEJĄCO ==========");
        List<Product> sortedByPrice = processor.sortByPriceDescending();
        sortedByPrice.forEach(product ->
                System.out.printf("%s (%.2f zł)\n", product.getName(), product.getPrice()));

        System.out.println("\n========== STATYSTYKI ==========");
        processor.printProductStats();
    }

    private static List<Product> loadProductsFromFile(String filename) {
        List<Product> list = new ArrayList<>();
        
        try (Scanner fileReader = new Scanner(Paths.get(filename)))
        {  
            String readTo;
            while (fileReader.hasNextLine())
            {
                readTo = fileReader.nextLine();
                try {
                    Product product = parseProduct(readTo);
                    list.add(product);
                } catch ( IOException ex) {
                    System.out.println("\nError reading a line\n");
                }


            }
        
        } catch ( IOException e) {
            System.out.println("error - file not found");
        }

        return list;

    }
    private static Product parseProduct(String line) throws IOException {
        String name;
        double price;
        String category;

        String[] strs = line.split(";");

        if (strs.length != 3)
            throw new IOException();
    
        name = strs[0].trim();
        price = Double.valueOf(strs[1].trim());
        category = strs[2].trim();

        return new Product(name, price, category);

    }
}

//Product{name='Laptop Dell XPS', price=5999,99, category='Elektronika'}
//Product{name='Klawiatura mechaniczna', price=349,99, category='Akcesoria komputerowe'}
//Product{name='Mysz bezprzewodowa', price=129,99, category='Akcesoria komputerowe'}
//Product{name='Słuchawki Sony', price=799,00, category='Audio'}
//Product{name='Smartfon Samsung', price=2499,00, category='Elektronika'}
//Product{name='Monitor 4K', price=1899,99, category='Elektronika'}
//Product{name='Głośnik Bluetooth', price=279,99, category='Audio'}
//Product{name='Pendrive 128GB', price=89,99, category='Akcesoria komputerowe'}
//Product{name='Tablet Apple', price=2899,00, category='Elektronika'}
//Product{name='Mikrofon studyjny', price=599,00, category='Audio'}
//Product{name='Kabel HDMI', price=49,99, category='Akcesoria komputerowe'}
//Product{name='Słuchawki gamingowe', price=349,00, category='Audio'}
//Product{name='Telewizor 55"', price=3999,00, category='Elektronika'}
//Product{name='Powerbank 20000mAh', price=199,99, category='Akcesoria komputerowe'}
//Product{name='Kamera internetowa', price=299,00, category='Elektronika'}
//
//========== GRUPOWANIE WEDŁUG KATEGORII ==========
//
//Kategoria: Akcesoria komputerowe
//  - Product{name='Klawiatura mechaniczna', price=349,99, category='Akcesoria komputerowe'}
//  - Product{name='Mysz bezprzewodowa', price=129,99, category='Akcesoria komputerowe'}
//  - Product{name='Pendrive 128GB', price=89,99, category='Akcesoria komputerowe'}
//  - Product{name='Kabel HDMI', price=49,99, category='Akcesoria komputerowe'}
//  - Product{name='Powerbank 20000mAh', price=199,99, category='Akcesoria komputerowe'}
//
//Kategoria: Audio
//  - Product{name='Słuchawki Sony', price=799,00, category='Audio'}
//  - Product{name='Głośnik Bluetooth', price=279,99, category='Audio'}
//  - Product{name='Mikrofon studyjny', price=599,00, category='Audio'}
//  - Product{name='Słuchawki gamingowe', price=349,00, category='Audio'}
//
//Kategoria: Elektronika
//  - Product{name='Laptop Dell XPS', price=5999,99, category='Elektronika'}
//  - Product{name='Smartfon Samsung', price=2499,00, category='Elektronika'}
//  - Product{name='Monitor 4K', price=1899,99, category='Elektronika'}
//  - Product{name='Tablet Apple', price=2899,00, category='Elektronika'}
//  - Product{name='Telewizor 55"', price=3999,00, category='Elektronika'}
//  - Product{name='Kamera internetowa', price=299,00, category='Elektronika'}
//
//========== ŚREDNIA CENA W KATEGORIACH ==========
//Akcesoria komputerowe: 163,99 zł
//Audio: 506,75 zł
//Elektronika: 2932,66 zł
//
//========== NAJDROŻSZE PRODUKTY W KATEGORIACH ==========
//Akcesoria komputerowe: Klawiatura mechaniczna (349,99 zł)
//Audio: Słuchawki Sony (799,00 zł)
//Elektronika: Laptop Dell XPS (5999,99 zł)
//
//========== PRODUKTY Z CENĄ POWYŻEJ ŚREDNIEJ ==========
//Product{name='Laptop Dell XPS', price=5999,99, category='Elektronika'}
//Product{name='Smartfon Samsung', price=2499,00, category='Elektronika'}
//Product{name='Monitor 4K', price=1899,99, category='Elektronika'}
//Product{name='Tablet Apple', price=2899,00, category='Elektronika'}
//Product{name='Telewizor 55"', price=3999,00, category='Elektronika'}
//
//========== PRODUKTY POSORTOWANE WEDŁUG CENY MALEJĄCO ==========
//Laptop Dell XPS (5999,99 zł)
//Telewizor 55" (3999,00 zł)
//Tablet Apple (2899,00 zł)
//Smartfon Samsung (2499,00 zł)
//Monitor 4K (1899,99 zł)
//Słuchawki Sony (799,00 zł)
//Mikrofon studyjny (599,00 zł)
//Klawiatura mechaniczna (349,99 zł)
//Słuchawki gamingowe (349,00 zł)
//Kamera internetowa (299,00 zł)
//Głośnik Bluetooth (279,99 zł)
//Powerbank 20000mAh (199,99 zł)
//Mysz bezprzewodowa (129,99 zł)
//Pendrive 128GB (89,99 zł)
//Kabel HDMI (49,99 zł)
//
//========== STATYSTYKI ==========
//Laptop Dell XPS
//Klawiatura mechaniczna
//Mysz bezprzewodowa
//Słuchawki Sony
//Smartfon Samsung
//Monitor 4K
//Głośnik Bluetooth
//Pendrive 128GB
//Tablet Apple
//Mikrofon studyjny
//Kabel HDMI
//Słuchawki gamingowe
//Telewizor 55"
//Powerbank 20000mAh
//Kamera internetowa
//
//Statystyki produktów:
//Liczba produktów: 15
//Cena minimalna: 49.99
//Cena maksymalna: 5999.99
//Średnia cena: 1362.8613333333333
//Suma cen: 20442.92
//
//Liczba produktów w kategoriach:
//Akcesoria komputerowe: 5
//Audio: 4
//Elektronika: 6