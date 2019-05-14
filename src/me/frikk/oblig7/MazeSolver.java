package me.frikk.oblig7;

import javafx.geometry.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;


public class MazeSolver extends Application {
    Labyrint labyrint;
    GridPane rutenett;
    int teller = 0;
    protected static List<List<Rute>> losninger;

    @Override
    public void start(Stage stage) throws Exception {
        rutenett = new GridPane();

        FileChooser fc = new FileChooser();
        File fil = fc.showOpenDialog(null);
        try {
            labyrint = Labyrint.lesFraFil(fil);
            this.leggTilIRutenett(labyrint);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Button nullstillKnapp = new Button("Nullstill");
        Button nesteLoesning = new Button("Neste");
        Button forrigeLoesning = new Button("Forrige");

        nesteLoesning.setOnAction(e -> {
            labyrint.nullstillBrett();
            tegnLoesning(losninger.get(teller++));
        });

        nullstillKnapp.setOnAction(e -> labyrint.nullstillBrett());

        Pane root = new Pane();
        root.setPrefSize(500, 500);

        BorderPane borderPane = new BorderPane();
        HBox knappRad = new HBox();
        knappRad.getChildren().addAll(nullstillKnapp, nesteLoesning, forrigeLoesning);
        //border.setTop(top);
        //top.setStyle(borderBox);
        knappRad.setPadding(new Insets(10, 5, 10, 5));

        borderPane.setCenter(rutenett);
        borderPane.setBottom(knappRad);
        root.getChildren().add(borderPane);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public void leggTilIRutenett(Labyrint labyrint) {
        for (Rute[] rad : labyrint.hentRuter()) {
            for (Rute rute : rad) {
                rutenett.add(rute, rute.kolonne, rute.rad);
            }
        }
    }

    public static void tegnLoesning(List<Rute> ruter) {
        for (Rute rute : ruter) {
            rute.setFill(Color.GREEN);
        }
    }
    
}