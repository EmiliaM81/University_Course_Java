
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//Zaimplementuj schemat klas pokazany na diagramie wykorzystując typy generyczne, tak by uzyskać output
// pokazany poniżej.
//Wkazówka:
//Oto deklaracje mniej oczywistych klas:
//public class Container<T extends LibraryItem> implements Iterable<T>
//public class Book implements LibraryItem
//public class LibraryItemIterator<T extends LibraryItem> implements Iterator<T>


public class Main {
    public static void main(String[] args) {
//        Library library = new Library();

        // Create and add books using generic container
        Container<Book> bookContainer = new Container<>();
        bookContainer.addItem(new Book("978-0132350884", "Clean Code", "Robert C. Martin",1987));
        bookContainer.addItem(new Book("111-4444444444", "Effective Java", "Joshua Bloch",2005));
        bookContainer.addItem(new Book("222-1111111111", "Thinking in Java", "Bruce Eckel",2000));

        System.out.println("Niejawne wykorzystanie iteratora");
        for (Book book : bookContainer) {
            System.out.println(book);
        }

        System.out.println("Jawne wykorzystanie iteratora");
        Iterator<Book> bookIterator = bookContainer.iterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            System.out.println(book);
        }

        List<Book> books = bookContainer.getItems();

        System.out.println("Sortuj po tytule i wypisz");
        Collections.sort(books, new TitleComparator());
        LibraryUtils.printItems(books);

        System.out.println("Sortuj po roku wydania i wypisz");
        Collections.sort(books, new YearComparator());
        LibraryUtils.printItems(books);


        System.out.println("Sortuj po autorze i wypisz");
        Collections.sort(books, new AuthorComparator());
        LibraryUtils.printItems(books);


        System.out.println("Użyj metody z parametrem wieloznacznym");
        List<? extends LibraryItem> items = books;
        LibraryUtils.printItems(items);
    }
}

//Niejawne wykorzystanie iteratora
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Jawne wykorzystanie iteratora
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Sortuj po tytule i wypisz
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Sortuj po roku wydania i wypisz
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Sortuj po autorze i wypisz
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}
//Użyj metody z parametrem wieloznacznym
//Book{isbn='222-1111111111', title='Thinking in Java', author='Bruce Eckel', publicationYear=2000}
//Book{isbn='111-4444444444', title='Effective Java', author='Joshua Bloch', publicationYear=2005}
//Book{isbn='978-0132350884', title='Clean Code', author='Robert C. Martin', publicationYear=1987}