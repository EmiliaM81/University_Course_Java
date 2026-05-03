import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    ObservableList<String> lista = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<>(lista);
    Label status = new Label("Status");

    @Override
    public void start(Stage stage) {

        TextField pole = new TextField();

        Button dodaj = new Button("Dodaj");
        Button usun = new Button("Usuń");
        Button wyczysc = new Button("Wyczyść");
        Button zapisz = new Button("Zapisz");


        dodaj.setOnAction(e -> {
            String tekst = pole.getText();
            if (!tekst.isEmpty()) {
                lista.add(tekst);
                pole.clear();
                status.setText("Dodano");
            }
        });


        usun.setOnAction(e -> {
            String wybrane = listView.getSelectionModel().getSelectedItem();
            if (wybrane != null) {
                lista.remove(wybrane);
                status.setText("Usunięto");
            }
        });


        wyczysc.setOnAction(e -> {
            lista.clear();
            status.setText("Wyczyszczono");
        });


        zapisz.setOnAction(e -> {
            try {
                BufferedWriter w = new BufferedWriter(new FileWriter("zadania.txt"));
                for (String s : lista) {
                    w.write(s);
                    w.newLine();
                }
                w.close();
                status.setText("Zapisano");
            } catch (Exception ex) {
                status.setText("Błąd zapisu");
            }
        });


        try {
            BufferedReader r = new BufferedReader(new FileReader("zadania.txt"));
            String linia;
            while ((linia = r.readLine()) != null) {
                lista.add(linia);
            }
            r.close();
            status.setText("Wczytano");
        } catch (Exception e) {
            status.setText("Brak pliku");
        }

        VBox root = new VBox(5, pole, dodaj, usun, wyczysc, zapisz, listView, status);

        stage.setScene(new Scene(root, 300, 400));
        stage.setTitle("To-Do");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

