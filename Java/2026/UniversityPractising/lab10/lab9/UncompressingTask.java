import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UncompressingTask implements Runnable {
    private final String destFolder;
    private final File f;

    public UncompressingTask(File f, String destFolder) {
        this.destFolder = destFolder;
        this.f = f;
    }

    @Override
    public void run() {
        long zipSizeBytes = f.length();
        long extractedSizeBytes = 0;

        try (FileInputStream fis = new FileInputStream(f);
             ZipInputStream zis = new ZipInputStream(fis)) {
             
            ZipEntry entry = zis.getNextEntry();
            
            if (entry != null) {
                File unzippedFile = new File(destFolder, entry.getName());

                try (FileOutputStream fos = new FileOutputStream(unzippedFile)) {
                    byte[] readBuffer = new byte[8192];
                    int chunkLength;
                    
                    while ((chunkLength = zis.read(readBuffer)) != -1) {
                        fos.write(readBuffer, 0, chunkLength);
                    }
                }
                
                extractedSizeBytes = unzippedFile.length();
            }
            zis.closeEntry();
            
        } catch (Exception ex) {
            System.err.println("Błąd podczas dekompresji archiwum " + f.getName() + ": " + ex.getMessage());
            return;
        }

        double archiveMb = zipSizeBytes / 1048576.0;
        double uncompressedMb = extractedSizeBytes / 1048576.0;

        String workerName = Thread.currentThread().getName();

        System.out.printf(Locale.forLanguageTag("pl-PL"), 
                "Archiwum %s rozpakowane przez wątek%s. Rozmiar archiwum: %.1f MB, rozmiar po rozpakowaniu: %.1f MB%n",
                f.getName(), workerName, archiveMb, uncompressedMb);
    }
}

