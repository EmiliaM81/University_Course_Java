import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LibraryItemIterator<T extends LibraryItem> implements Iterator<T>
{
    private List<T> list;
    private int index;

    public LibraryItemIterator(List<T> list)
    {
        this.list = list;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() throws NoSuchElementException {
        if (this.hasNext())
        {
            return list.get(index++);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        list.remove(--index); // ? DLACZEGO
    }
}