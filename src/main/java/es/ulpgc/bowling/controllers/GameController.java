package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.config.Color;
import es.ulpgc.bowling.entity.PlayerEntity;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    private final GuiController gc;
    private ArrayList<Label> playerLabels;
    private ArrayList<HBox> playerBoxes;

    public GameController(GuiController guiController) {
        this.gc = guiController;
        playerLabels = new ArrayList<>();
        playerBoxes = new ArrayList<>();
        initGameGui();
    }

    private void initGameGui() {
        List<PlayerEntity> players = gc.getCurrentGame().getPlayers();

        for (int i = 0; i < players.size(); i++) {
            Label l = new Label(players.get(i).getName());
            l.setStyle("-fx-font: bold 14px System");

            HBox h = new HBox();
            h.setPrefHeight(50);
            h.setAlignment(Pos.CENTER_LEFT);
            for (int j = 0; j <= 10; j++) {
                AnchorPane ap = new AnchorPane();
                ap.setMinSize(55, 50);

                if (j == 0) ap.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 2; -fx-background-radius: 5; -fx-border-radius: 2; " +
                        "-fx-background-color: " + Color.values()[i].getColor() + ";");
                else if (j == 10) ap.setStyle("-fx-border-color: black; -fx-border-width: 2 2 2 1; -fx-background-radius: 5; -fx-border-radius: 2; " +
                        "-fx-background-color: white;");
                else ap.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-background-radius: 5; -fx-border-radius: 2; -fx-background-color: " +
                            Color.values()[i].getColor() + ";");

                prepareLabels(ap, j);
                HBox.setHgrow(ap, Priority.ALWAYS);
                h.getChildren().add(ap);
            }


            gc.gameVBox.getChildren().addAll(l, h);
            playerLabels.add(l);
            playerBoxes.add(h);
        }

        Pane filler = new Pane();
        VBox.setVgrow(filler, Priority.ALWAYS);

        HBox rollBox = new HBox();
        Label rollOut = new Label();
        HBox.setMargin(rollOut,new Insets(0,0,0,20));
        rollOut.setStyle("-fx-font: bold 22px System");

        Button rollLab = new Button("Roll!");
        rollLab.setOnAction(e->{
            Random r = new Random();
            rollOut.setText(r.nextInt(11)+"");
        });
        rollBox.getChildren().addAll(rollLab, rollOut);

        gc.gameVBox.getChildren().addAll(filler, rollBox);

        startGame();
    }

    private void prepareLabels(AnchorPane ap, int pos) {
        Label sc1 = new Label();
        Label sc2 = new Label();
        Label sc3 = new Label();
        Pane pn = new Pane();

        switch (pos) {
            case 10:
                sc3.setAlignment(Pos.CENTER);
                sc3.setStyle("-fx-font: bold 18px System; -fx-text-alignment: center");

                AnchorPane.setBottomAnchor(sc3, 0D);
                AnchorPane.setLeftAnchor(sc3, 0D);
                AnchorPane.setRightAnchor(sc3, 0D);
                AnchorPane.setTopAnchor(sc3, 0D);
                ap.getChildren().add(sc3);
                break;
            case 9:
                Label sc4 = new Label();
                Pane pn2 = new Pane();
                sc4.setAlignment(Pos.CENTER);
                sc4.setStyle("-fx-font: bold 18px System;");

                pn2.setPrefSize(20, 20);
                pn2.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 0 0 1 1; -fx-border-radius: 2; -fx-background-radius: " +
                        "2;");

                AnchorPane.setTopAnchor(pn2, 0D);
                AnchorPane.setRightAnchor(pn2, 20D);

                AnchorPane.setRightAnchor(sc4, 23D);
                AnchorPane.setTopAnchor(sc4, -4D);
                ap.getChildren().addAll(pn2, sc4);
            default:
                sc1.setAlignment(Pos.CENTER);
                sc1.setStyle("-fx-font: bold 18px System; -fx-text-fill: white;");
                sc2.setAlignment(Pos.CENTER);
                sc2.setStyle("-fx-font: bold 18px System;");
                sc3.setAlignment(Pos.CENTER);
                sc3.setStyle("-fx-font: bold 18px System; -fx-text-fill: white;");

                pn.setPrefSize(20, 20);
                pn.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 0 0 1 1; -fx-border-radius: 2; -fx-background-radius: 2;");

                AnchorPane.setTopAnchor(pn, 0D);
                AnchorPane.setRightAnchor(pn, 0D);

                AnchorPane.setBottomAnchor(sc1, 27D);
                AnchorPane.setLeftAnchor(sc1, 0D);
                AnchorPane.setTopAnchor(sc1, -4D);
                if (pos != 9) AnchorPane.setRightAnchor(sc1, 20D);
                else AnchorPane.setRightAnchor(sc1, 40D);

                AnchorPane.setRightAnchor(sc2, 3D);
                AnchorPane.setTopAnchor(sc2, -4D);

                AnchorPane.setBottomAnchor(sc3, 0D);
                AnchorPane.setLeftAnchor(sc3, 0D);
                AnchorPane.setRightAnchor(sc3, 0D);

                ap.getChildren().addAll(sc1, pn, sc2, sc3);
        }
    }

    private void startGame() {
        System.out.println(gc.gameVBox.getChildren());
    }
}
