/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jamlikepro;

import jamlikepro.sound.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author grabli66
 */
public class JamLikePro extends Application {

    private final CompositionPlayer compPlayer = new CompositionPlayer();
    private final Composition composition = new Composition();

    private static class BeatGridData {

        public Integer x;
        public Integer y;
    }

    private String getSampleByIdx(Integer idx) throws Exception {
        switch (idx) {
            case 4:
                return "samples/snare.wav";
            case 5:
                return "samples/kick.wav";
        }

        throw new Exception("Sample not found");
    }

    private final EventHandler<ActionEvent> beatButtonHandler = (ActionEvent event) -> {
        ToggleButton butt = (ToggleButton) (event.getSource());
        BeatGridData gridData = (BeatGridData) (butt.getUserData());
        Beat beat = composition.getBeat(0);

        try {
            if (butt.isSelected()) {
                beat.addNote(gridData.x, getSampleByIdx(gridData.y));
            } else {
                beat.removeNote(gridData.x, getSampleByIdx(gridData.y));
            }
        } catch (Exception e) {            
        }
    };

    /*
    *   Создает таблицу с битами
     */
    private Node layoutBeatGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 0, 0));

        grid.getColumnConstraints().add(new ColumnConstraints(60));

        grid.add(new Label("Tom 1"), 0, 0);
        grid.add(new Label("Tom 2"), 0, 1);
        grid.add(new Label("Tom 3"), 0, 2);
        grid.add(new Label("Hi-Hat"), 0, 3);
        grid.add(new Label("Snare"), 0, 4);
        grid.add(new Label("Kick"), 0, 5);

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 6; k++) {
                ToggleButton butt = new ToggleButton("...");
                butt.getStyleClass().add("beat-button");
                BeatGridData dat = new BeatGridData();
                dat.x = i;
                dat.y = k;
                butt.setUserData(dat);
                butt.setOnAction(beatButtonHandler);
                grid.add(butt, i + 1, k);
            }
        }
        return grid;
    }

    /*
    * Создает все графические компоненты
     */
    private Parent layout(VBox root) {        
        root.setPadding(new Insets(5, 5, 5, 10));

        ToggleButton btn = new ToggleButton();        
        btn.getStyleClass().add("start-button");        
        btn.setText("Start");
        btn.setOnAction((ActionEvent event) -> {
            if (btn.isSelected()) {
                compPlayer.playComposition(composition, true);
            } else {
                compPlayer.stopPlaying();
            }
        });
        root.getChildren().add(btn);

        Node beatGrid = layoutBeatGrid();
        root.getChildren().add(beatGrid);

        return root;
    }

    private void prepareComposition() {
        Beat beat = new Beat();
        composition.addBeat(beat);
    }

    @Override
    public void start(Stage primaryStage) {
        prepareComposition();

        VBox root = new VBox();       
        Scene scene = new Scene(root, 740, 300);
        scene.getStylesheets().add("resources/css/main.css");
        layout(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Jam Like Pro 1.0");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
