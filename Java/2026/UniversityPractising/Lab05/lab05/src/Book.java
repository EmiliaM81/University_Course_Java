public class Book implements LibraryItem {
    private String id;
    private String title;
    private String author;
    private int publicationYear;
    private boolean avilable;

    public Book(String id, String title, String author, int publicationYear)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.avilable = true;
    }

    @Override
    public boolean isAvailable() {
        return avilable;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getAuthor()
    {
        return author;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getPublicationYear()
    {
        return publicationYear;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{isbn='" + id + "', title='" + title +"', author=" + author +"', publicationYear=" + publicationYear + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*id.hashCode();
        return hash;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setAvailable(boolean value)
    {
        avilable = true;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if ( !(obj instanceof Book))
            return false;

        Book cpy = (Book) obj;
        if (cpy.getId().equals(getId()))
            return true;

        return false;
    }
}
