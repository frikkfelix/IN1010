package me.frikk.oblig7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ScrollPane;

public class MazeSolver extends Application {
    Labyrint labyrint;
    List<List<Rute>> loesninger;
    GridPane rutenett;
    List<List<GUIRute>> guiRuter;
    int teller = 0;
    Text antallLoesningerText = new Text("Klikk på en rute");
    Text loesningAvLoesningerText = new Text("0/0");

    @Override
    public void start(Stage stage) throws Exception {
        rutenett = new GridPane();
        rutenett.setAlignment(Pos.CENTER);

        FileChooser fc = new FileChooser();
        File fil = fc.showOpenDialog(null);
        try {
            labyrint = Labyrint.lesFraFil(fil);
            this.tegnLabyrint(labyrint);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        ScrollPane scrollPane = new ScrollPane();

        Button nullstillKnapp = new Button("Nullstill");
        Button nesteLoesning = new Button("→");
        Button forrigeLoesning = new Button("←");

        VBox overskrifter = new VBox();
        overskrifter.setPadding(new Insets(10));
        Text header = new Text("Maze Solver");
        overskrifter.getChildren().addAll(header, antallLoesningerText);
        overskrifter.setAlignment(Pos.CENTER);

        header.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        antallLoesningerText.setFont(Font.font("Helvetica", 20));
        loesningAvLoesningerText.setFont(Font.font("Helvetica", 20));

        nullstillKnapp.setOnAction(e -> {
            teller = 0;
            antallLoesningerText.setText("Klikk på en rute");
            loesningAvLoesningerText.setText("0/0");
            if (this.loesninger != null) {
                this.loesninger.clear();
            }
            this.nullstillBrett();
        });

        nesteLoesning.setOnAction(e -> {
            this.nullstillBrett();
            if (this.loesninger != null) {
                teller = teller >= loesninger.size() - 1 ? 0 : teller + 1;
                loesningAvLoesningerText.setText(String.format("%d/%d", teller + 1, this.loesninger.size()));
                this.tegnLoesning(loesninger.get(teller));
            }
        });

        forrigeLoesning.setOnAction(e -> {
            this.nullstillBrett();
            if (this.loesninger != null) {
                teller = teller <= 0 ? loesninger.size() - 1 : teller - 1;
                loesningAvLoesningerText.setText(String.format("%d/%d", teller + 1, this.loesninger.size()));
                this.tegnLoesning(loesninger.get(teller));
            }
        });

        VBox knapper = new VBox();
        HBox forrigeNeste = new HBox();
        forrigeNeste.getChildren().addAll(forrigeLoesning, nesteLoesning);
        knapper.getChildren().addAll(loesningAvLoesningerText, forrigeNeste, nullstillKnapp);

        forrigeNeste.setAlignment(Pos.CENTER);
        knapper.setAlignment(Pos.CENTER);
        knapper.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(rutenett);
        borderPane.setBottom(knapper);
        borderPane.setTop(overskrifter);

        scrollPane.setContent(borderPane);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        Scene scene = new Scene(scrollPane);
        stage.setTitle("MazeSolver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void finnLoesning(int kolonne, int rad) {
        teller = 0;
        if (this.loesninger != null) {
            this.loesninger.clear();
        }
        this.nullstillBrett();
        this.loesninger = labyrint.finnUtveiFra(kolonne, rad);
        if (this.loesninger.size() != 0) {
            String tekst = this.loesninger.size() == 1 ? "løsning" : "løsninger";
            antallLoesningerText.setText(String.format("%d %s", this.loesninger.size(), tekst));
            loesningAvLoesningerText.setText(String.format("%d/%d", teller + 1, this.loesninger.size()));
            tegnLoesning(this.loesninger.get(teller));
        } else {
            antallLoesningerText.setText(String.format("Ingen løsninger", this.loesninger.size()));
            loesningAvLoesningerText.setText("0/0");
        }
    }

    private void tegnLoesning(List<Rute> ruter) {
        for (Rute rute : ruter) {
            this.guiRuter.get(rute.rad).get(rute.kolonne).setFill(Color.GREEN);
        }
    }

    private void tegnLabyrint(Labyrint labyrint) {
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

    private void nullstillBrett() {
        for (List<GUIRute> guiRuteListe : guiRuter) {
            for (GUIRute guiRute : guiRuteListe) {
                guiRute.resettFarge();
            }
        }
    }
}
