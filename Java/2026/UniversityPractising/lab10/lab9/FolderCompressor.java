
import java.io.File;


// oddzielnie plik po pliku i zapisane w folderze docelowym.
//Zipowanie każdego pliku musi odbyć się w oddzielnym wątku wykonującym zadanie CompressingTask implementujące interfejs
// Runnable (uwaga: to niejest zaznaczone na diagramie UML) i skutkować:
//1. Outputem jak poniżej.
//2. Zapisaniem plików w folderze compressed
public class FolderCompressor {
    public static void main(String[] arguments) {
//        ???
        if (arguments.length != 2) {
            System.out.println("Błędne wywołanie");
            System.out.println("Wywołuje się: java program [sourcePath] [destPath]");
            return;
        }
        
        String inputDir = arguments[0];
        String outputDir = arguments[1];

        File folderIn = new File(inputDir);
        if (!folderIn.isDirectory() || !folderIn.exists()) {
            System.out.println("Folder źródłowy nie istnieje bądź nie jest folderem");
            return;
        }
        
        File folderOut = new File(outputDir);
        if (!folderOut.exists()) {
            folderOut.mkdirs();
        }

        File[] directoryFiles = folderIn.listFiles();
        
        if (directoryFiles == null || directoryFiles.length == 0) {
            System.out.println("Brak plików w folderze źródłowym");
            return;
        }

        for (File currentFile : directoryFiles) {
            if (currentFile.isFile()) {
                Runnable worker = new CompressingTask(currentFile, outputDir);
                new Thread(worker).start();
            }
        }
    } 
}
//
//Plik Angular2_Succinctly.pdf zzipowany w wątku Thread-1. Rozmiar początkowy: 2,1 MB,rozmiar końcowy: 1,9 MB
//Plik Android-UI-Design.pdf zzipowany w wątku Thread-2. Rozmiar początkowy: 3,9 MB,rozmiar końcowy: 3,4 MB
//Plik Android-Programming-Cookbook.pdf zzipowany w wątku Thread-0. Rozmiar początkowy: 8,6 MB,rozmiar końcowy: 8,0 MB

