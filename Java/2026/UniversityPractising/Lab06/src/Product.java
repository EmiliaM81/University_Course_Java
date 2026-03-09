public class Product {
    private String name;
    private double price;
    private String category;

    Product(String name ,double price, String category)
    {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{name='" + name + "', price=" + price + ", category='" + category + "'}";
    }
}
