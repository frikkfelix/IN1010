package me.frikk.oblig8;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

class GUIRute extends Rectangle {
    Rute rute;
    MazeSolver mazeSolver;

    public GUIRute(Rute rute, MazeSolver mazeSolver) {
        super(30, 30, hentFarge(rute));
        this.setOnMouseClicked(e -> klikk());
        this.rute = rute;
        this.mazeSolver = mazeSolver;
    }

    private void klikk() {
        mazeSolver.finnLoesning(rute.kolonne, rute.rad);
    }

    static Color hentFarge(Rute rute) {
        return rute instanceof HvitRute ? Color.WHITE : Color.BLACK;
    }

    void resettFarge() {
        this.setFill(hentFarge(rute));
    }
}