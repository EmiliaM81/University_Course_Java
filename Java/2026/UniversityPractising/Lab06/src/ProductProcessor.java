import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductProcessor {
    private List<Product> products;

    public ProductProcessor(List<Product> products)
    {
        this.products = products;
    }

    public List<Product> filterProductsAboveAveragePrice()
    {
        double avgPrice = products.stream().mapToDouble(product -> product.getPrice()).average().getAsDouble();
        return products.stream().filter(product -> product.getPrice() > avgPrice).toList();
    }


    public Map<String, List<Product>> groupByCategory()
    {
        return products.stream().collect(Collectors.groupingBy(Product::getCategory));
    }

    public List<Product> sortByPriceDescending()
    {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).toList();
    }

    public Map<String, Double> calculateAveragePriceByCategory()
    {
        return products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
    }

    public Map<String, Product> findMostExpensiveByCategory()
    {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                                Optional::get
                        )
                ));
    }
    public void printProductStats()
    {
        DoubleSummaryStatistics stats = products.stream()
                .collect(Collectors.summarizingDouble(Product::getPrice));

        System.out.println("Statystyki produktów:");
        System.out.println("Liczba produktów: " + stats.getCount());
        System.out.println("Cena minimalna: " + stats.getMin());
        System.out.println("Cena maksymalna: " + stats.getMax());
        System.out.println("Średnia cena: " + stats.getAverage());
        System.out.println("Suma cen: " + stats.getSum());

        System.out.println("\nLiczba produktów w kategoriach:");

        Map<String, Long> categoryCount = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));

        categoryCount.forEach((category, count) ->
                System.out.println(category + ": " + count));
    }


}
