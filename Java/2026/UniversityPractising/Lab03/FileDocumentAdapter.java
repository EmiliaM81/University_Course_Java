import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class FileDocumentAdapter implements Document {
    private String content;
    private TextDocument textDocument;

    public FileDocumentAdapter(String filename) throws IOException
    {
        this.textDocument = readDocumentFromFile(filename);
    }

    private TextDocument readDocumentFromFile(String filename) throws IOException
    {
        File file = new File(filename);
        String content = "";
        String name = "";
        try (Scanner scanner = new Scanner(file))
        {
            if (scanner.hasNextLine())
                name = scanner.nextLine().trim();
            while (scanner.hasNextLine())
            {
                content += scanner.nextLine().trim() + "\n";
                
            }
        } 
        this.content = content;

        return new TextDocument(name, content);
        
    }

    @Override
    public void display() 
    {
        textDocument.display();
        
    }

    @Override
    public String getContent() {
        return content;
    }
}
