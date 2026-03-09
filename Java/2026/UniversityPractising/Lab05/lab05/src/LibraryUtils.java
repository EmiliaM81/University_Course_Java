import java.util.Collection;
import java.util.Iterator;

public class LibraryUtils {
    public static <T> void printItems(Collection<T> collection)
    {
        Iterator<T> iterator= collection.iterator();
        while(iterator.hasNext())
        {
            T item = iterator.next();
            System.out.println(item);
        }

    }
}
