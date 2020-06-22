package com.imaginarium.gameoflife;

import java.util.Arrays;

import com.imaginarium.gameoflife.components.PrimordialSea;
import com.imaginarium.gameoflife.enums.BirthType;
import com.imaginarium.gameoflife.utils.Context;
import com.imaginarium.gameoflife.utils.CycleOfLife;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Gui extends VBox {

    private HBox titleBox;
    private HBox topMenuBox;
    private HBox controlsBox;
    private Context context;
    private CycleOfLife lifeCycle;
    
    public Gui(Context ctx, CycleOfLife lifeCycle) {
        titleBox = new HBox();
        topMenuBox = new HBox();
        controlsBox = new HBox();
        context = ctx;
        this.lifeCycle = lifeCycle;

        setupTitle();
        setupButtons();

        getChildren().addAll(titleBox, topMenuBox, controlsBox);
    }

    private void setupTitle() {
        Label title = new Label("Conway's Game of Life");
        title.setFont(Font.font(24));
        title.setTextFill(Color.ORANGE);
        title.setAlignment(Pos.CENTER);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        titleBox.getChildren().add(title);
    }

    private void setupButtons() {
        PrimordialSea world = context.getWorld();
        LogicOfLife life = context.getLife();

        Button playBtn = new Button(lifeCycle.isRunning() ? "Pause" : "Play");
        playBtn.setPrefWidth(48);
        playBtn.setOnMouseClicked(e -> {
            if (lifeCycle.isRunning()) {
                lifeCycle.stop();
                playBtn.setText("Play");
            } else {
                lifeCycle.start();
                playBtn.setText("Pause");
            }
        });
        final Label speedSliderLabel = new Label("Speed:\n(Generations per second)");
        speedSliderLabel.setTextFill(Color.WHITE);
        speedSliderLabel.setTextAlignment(TextAlignment.CENTER);
        Slider speedSlider = new Slider(0, lifeCycle.getSPEED_MAX(), lifeCycle.getSpeed());
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(10);
        speedSlider.setMinorTickCount(4);
        speedSlider.setBlockIncrement(5);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPrefWidth(200);
        speedSlider.valueProperty().addListener( 
            new ChangeListener<Number>() {

            public void changed(ObservableValue <? extends Number >  
                     observable, Number oldValue, Number newValue) 
            { 
                int newSpeed = newValue.intValue();
                if (newSpeed > 0) {
                    lifeCycle.setSpeed(newSpeed);
                }
            } 
        });
        Button liveBtn = new Button("Cycle life");
        liveBtn.setOnMouseClicked(e -> {
            life.live(world);
        });
        controlsBox.setPrefHeight(40);
        controlsBox.setSpacing(20);
        controlsBox.setAlignment(Pos.CENTER);
        controlsBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        controlsBox.getChildren().addAll(speedSliderLabel, speedSlider, liveBtn, playBtn);

        final Label patternChoiceLabel = new Label("Starting pattern:");
        patternChoiceLabel.setTextFill(Color.WHITE);
        patternChoiceLabel.setTextAlignment(TextAlignment.CENTER);
        ChoiceBox<BirthType> startPatternChoiceBox = new ChoiceBox<>();
        startPatternChoiceBox.getItems().addAll(Arrays.asList(BirthType.values()));
        startPatternChoiceBox.setOnAction(e -> {
            if (lifeCycle.isRunning()) {
                lifeCycle.stop();
                playBtn.setText("Play");
            }
            life.giveBirth(world, startPatternChoiceBox.getValue());
        });
        startPatternChoiceBox.setValue(BirthType.RANDOM);
        startPatternChoiceBox.setPrefWidth(128);

        topMenuBox.setPrefHeight(40);
        topMenuBox.setSpacing(20);
        topMenuBox.setAlignment(Pos.CENTER);
        topMenuBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        topMenuBox.getChildren().addAll(patternChoiceLabel, startPatternChoiceBox);
    }
}