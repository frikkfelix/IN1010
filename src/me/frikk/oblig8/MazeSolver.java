package me.frikk.oblig8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.stage.FileChooser;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MazeSolver extends Application {
    Labyrint labyrint;
    List<List<Rute>> loesninger;
    GridPane rutenett;
    List<List<GUIRute>> guiRuter;
    int teller = 0;


    @Override
    public void start(Stage stage) throws Exception {
        rutenett = new GridPane();

        FileChooser fc = new FileChooser();
        File fil = fc.showOpenDialog(null);
        try {
            labyrint = Labyrint.lesFraFil(fil);
            this.tegnLabyrint(labyrint);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Pane root = new Pane();
        root.setPrefSize(400, 500);

        Button nullstillKnapp = new Button("Nullstill");
        Button nesteLoesning = new Button("Neste");
        Button forrigeLoesning = new Button("Forrige");

        nullstillKnapp.setOnAction(e -> this.nullstillBrett());

        nesteLoesning.setOnAction(e -> {
            this.nullstillBrett();
            teller = teller >= loesninger.size() ? 0 : teller + 1;
            this.tegnLoesning(loesninger.get(teller));
        });

        HBox knappRad = new HBox();
        knappRad.getChildren().addAll(nullstillKnapp, forrigeLoesning, nesteLoesning);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(rutenett);
        borderPane.setBottom(knappRad);

        root.getChildren().add(borderPane);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    void finnLoesning(int kolonne, int rad) {
        if (this.loesninger != null) {
            this.loesninger.clear();
        }
        this.nullstillBrett();
        this.loesninger = labyrint.finnUtveiFra(kolonne, rad);
        tegnLoesning(this.loesninger.get(teller));
    }

    public void tegnLoesning(List<Rute> ruter) {
        for (Rute rute : ruter) {
            this.guiRuter.get(rute.rad).get(rute.kolonne).setFill(Color.GREEN);
        }
    }

    void tegnLabyrint(Labyrint labyrint) {
        this.guiRuter = Stream.of(labyrint.hentRuter())
            .map(rad -> Stream.of(rad)
                .map(rute -> {
                    GUIRute guiRute = new GUIRute(rute, this);
                    rutenett.add(guiRute, rute.kolonne, rute.rad);
                    return guiRute;
                })
                .collect(Collectors.toList())
            )
            .collect(Collectors.toList());
    }

    void nullstillBrett() {
        for (List<GUIRute> guiRuteListe : guiRuter) {
            for (GUIRute guiRute : guiRuteListe) {
                guiRute.resettFarge();
            }
        }
    }
}
