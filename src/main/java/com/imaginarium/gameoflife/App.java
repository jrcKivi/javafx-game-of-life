package com.imaginarium.gameoflife;

import com.imaginarium.gameoflife.components.PrimordialSea;
import com.imaginarium.gameoflife.utils.Context;
import com.imaginarium.gameoflife.utils.CycleOfLife;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private Rectangle2D screenInfo;
    private VBox root;
    private Gui menu;
    private PrimordialSea world;
    private LogicOfLife life;
    private Context context;
    private CycleOfLife lifeCycle;

    @Override
    public void init() {
        screenInfo = Screen.getPrimary().getBounds();
        root = new VBox(2);
        root.setAlignment(Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 119, 190, 0.75), null, null)));
        world = new PrimordialSea(192, 108);
        world.setAlignment(Pos.BASELINE_LEFT);
        life = new LogicOfLife();
        context = new Context(world, life);
        lifeCycle = new CycleOfLife(context);
        menu = new Gui(context, lifeCycle);
        root.getChildren().addAll(menu, world);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, screenInfo.getWidth() / 2, screenInfo.getHeight() / 2);
        stage.setScene(scene);
        stage.setTitle("Conway's Game of Life");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}