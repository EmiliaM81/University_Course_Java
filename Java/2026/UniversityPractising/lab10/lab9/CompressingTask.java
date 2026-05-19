import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressingTask implements Runnable {
    private final String outFolder;
    private final String originalFileName;
    private final File f;

    public CompressingTask(File f, String outFolder) {
        this.f = f;
        this.originalFileName = f.getName();
        this.outFolder = outFolder;
    }

    @Override
    public void run() {
        long startSizeBytes = f.length();
        File zippedFile = new File(outFolder, originalFileName + ".zip");

        try (FileInputStream fis = new FileInputStream(f);
             FileOutputStream fos = new FileOutputStream(zippedFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            ZipEntry entry = new ZipEntry(originalFileName);
            zos.putNextEntry(entry);

            byte[] dataBuffer = new byte[8192];
            int bytesRead;
            
            while ((bytesRead = fis.read(dataBuffer)) != -1) {
                zos.write(dataBuffer, 0, bytesRead);
            }
            zos.closeEntry();

        } catch (Exception ex) {
            System.err.println("Błąd podczas kompresji pliku " + originalFileName + ": " + ex.getMessage());
            return;
        }

        long endSizeBytes = zippedFile.length();

        double startMb = startSizeBytes / 1048576.0;
        double endMb = endSizeBytes / 1048576.0;

        String currentThreadId = Thread.currentThread().getName();

        System.out.printf(Locale.forLanguageTag("pl-PL"), 
                "Plik %s zzipowany w wątku %s. Rozmiar początkowy: %.1f MB,rozmiar końcowy: %.1f MB\n",
                originalFileName, currentThreadId, startMb, endMb);
    }
}
