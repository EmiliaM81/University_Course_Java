import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Container<T extends LibraryItem> implements Iterable<T> {
    private List<T> list;

    public Container()
    {
        list = new ArrayList<>();
    }

    public T removeItem(String id)
    {
        Iterator<T> it = this.iterator();
        while(it.hasNext())
        {
            T obj = it.next();
            if (obj.getId().equals(id))
            {
                it.remove();
                return obj;
            }
                
        }

        return null;
    }

    public void addItem(T item)
    {
        list.add(item);
    }

    public List<T> getItems()
    {
        return list;
    }

    public Iterator<T> iterator()
    {
        Iterator<T> iterator = new LibraryItemIterator<>(list);
        return iterator; 
    }


}
