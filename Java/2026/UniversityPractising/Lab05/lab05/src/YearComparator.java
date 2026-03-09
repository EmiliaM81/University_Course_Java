import java.util.Comparator;

public class YearComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPublicationYear() - o2.getPublicationYear();
    }
}
