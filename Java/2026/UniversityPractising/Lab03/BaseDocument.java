public abstract class BaseDocument implements Document {
    protected String filename;
    protected String content;

    public BaseDocument(String filename)
    {
        this.filename = filename;
    }
    
    public String getContent()
    {
        return content;
    }

    public void display()
    {
        
        System.out.println("Dokument: " + filename);
        System.out.println("Treść: " + getContent());
        System.out.println("---");
    }
}
