import java.util.ArrayList;
import java.util.List;

public class DocumentManager {
    private List<Document> documents;
    private DocumentManager()
    {
        this.documents = new ArrayList<>();
    }

    public void displayAllDocuments()
    {
        System.err.println("Lista dokumentow");
        documents.stream().forEach(document -> document.display());
    }

    public void addDocument(Document document)
    {
        documents.add(document);
    }

    public static DocumentManager getInstance()
    {
        return new DocumentManager();
    }
}
