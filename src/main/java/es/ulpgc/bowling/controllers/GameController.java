package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.config.Color;
import es.ulpgc.bowling.entity.FrameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {

    private final GuiController gc;
    private ArrayList<HBox> playerBoxes;
    private Label rollOut;
    private AtomicInteger gamePosition;
    private Random r;
    private int decay, tmp, playerCounter;
    private Button rollBut;

    public GameController(GuiController guiController) {
        this.gc = guiController;
        playerBoxes = new ArrayList<>();
        r = new Random();
        decay = 11;
        tmp = 0;
        playerCounter = 0;
        initGameGui();
    }

    private void initGameGui() {
        gc.gameVBox.getChildren().clear();
        List<PlayerEntity> players = gc.getCurrentGame().getPlayers();

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setGame(gc.getCurrentGame());
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
            playerBoxes.add(h);
        }

        Pane filler = new Pane();
        VBox.setVgrow(filler, Priority.ALWAYS);

        HBox rollBox = new HBox();

        rollOut = new Label();
        rollOut.setStyle("-fx-font: bold 22px System");
        HBox.setMargin(rollOut, new Insets(0, 0, 0, 20));

        rollBut = new Button("Roll!");
        rollBut.setOnAction(e -> makeRoll());

        rollBox.getChildren().addAll(rollBut, rollOut);

        gc.gameVBox.getChildren().addAll(filler, rollBox);
    }

    private void prepareLabels(AnchorPane ap, int pos) {
        Label sc1 = new Label();
        Label sc2 = new Label();
        Label sc3 = new Label();
        Pane pn = new Pane();

        if (pos == 10) {
            sc3.setAlignment(Pos.CENTER);
            sc3.setStyle("-fx-font: bold 18px System; -fx-text-alignment: center");

            AnchorPane.setBottomAnchor(sc3, 0D);
            AnchorPane.setLeftAnchor(sc3, 0D);
            AnchorPane.setRightAnchor(sc3, 0D);
            AnchorPane.setTopAnchor(sc3, 0D);
            ap.getChildren().add(sc3);
        } else {
            Label sc4 = new Label();
            Pane pn2 = new Pane();
            if (pos == 9) {
                sc4 = new Label();
                pn2 = new Pane();
                sc4.setAlignment(Pos.CENTER);
                sc4.setStyle("-fx-font: bold 18px System;");

                pn2.setPrefSize(20, 20);
                pn2.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 0 0 1 1; -fx-border-radius: 2; -fx-background-radius: " +
                        "2;");

                AnchorPane.setTopAnchor(pn2, 0D);
                AnchorPane.setRightAnchor(pn2, 20D);

                AnchorPane.setRightAnchor(sc4, 23D);
                AnchorPane.setTopAnchor(sc4, -4D);
            }
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

            ap.getChildren().addAll(pn, pn2, sc1, sc2, sc4, sc3);
        }
    }

    public void startGame() {
        gamePosition = new AtomicInteger();
        ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");

        LocalDateTime startTime = LocalDateTime.now();

        gc.clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            java.time.Duration duration = java.time.Duration.between(startTime, LocalDateTime.now());
            long seconds = duration.getSeconds();
            int hours = (int) (seconds / 3600);
            int minutes = (int) ((seconds % 3600) / 60);
            int secs = (int) (seconds % 60);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            gc.gameTime.setText(LocalDateTime.of(1, 1, 1, hours, minutes, secs).format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        gc.clock.setCycleCount(Animation.INDEFINITE);
        gc.clock.play();
    }

    private void makeRoll() {
        PlayerEntity p = gc.getCurrentGame().getPlayers().get(playerModulo());

        tmp = r.nextInt(decay);
//        tmp = 10;
//        tmp = 5;
        p.roll(tmp);
        rollOut.setText("You rolled: " + String.valueOf(tmp));

        writeScore(p);

        if (!((Label) ((AnchorPane) playerBoxes.get(gc.getCurrentGame().getPlayers().size() - 1).getChildren().get(10)).getChildren().get(0)).getText()
                .isEmpty()) {
            endGame();
            return;
        }

        if (tmp != 10 && decay == 11) {
            if ((p.getFrame(gamePosition.get()) != null && p.getFrame(gamePosition.get()).getRollTwo() != null)) {
                if (!(p.getFrame(gamePosition.get()).isLastFrame() && (p.getFrame(gamePosition.get()).isSpare() || p.getFrame(gamePosition.get()).isStrike())) || p
                        .getFrame(gamePosition.get())
                        .getRollThree()
                        != null) playerCounter++;

                if (playerModulo() != 0)
                    ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");
                else if (!p.getFrame(gamePosition.get()).isLastFrame()) ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().
                        get(gamePosition.get() + 1)).getChildren().get(5)).setText("> > >");

                if (!p.getFrame(gamePosition.get()).isLastFrame() && playerModulo() == 0) {
                    gamePosition.getAndIncrement();
                }
            } else decay -= tmp;
        } else {
            decay = 11;
            if (!(p.getFrame(gamePosition.get()).isLastFrame() && (p.getFrame(gamePosition.get()).isSpare() || p.getFrame(gamePosition.get()).isStrike())) || p
                    .getFrame(gamePosition.get())
                    .getRollThree()
                    != null) playerCounter++;

            if (playerModulo() != 0)
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");
            else if (!p.getFrame(gamePosition.get()).isLastFrame()) ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().
                    get(gamePosition.get() + 1)).getChildren().get(5)).setText("> > >");

            if (!p.getFrame(gamePosition.get()).isLastFrame() && playerModulo() == 0) {
                gamePosition.getAndIncrement();
            }
        }
    }

    private void endGame() {
        rollBut.setDisable(true);
        rollOut.setText("Game ended!");
        gc.getCurrentGame().setEnded(LocalDateTime.now());
        gc.getGameRepo().save(gc.getCurrentGame());
        gc.clock.stop();
    }

    private int playerModulo() {
        return playerCounter % gc.getCurrentGame().getPlayers().size();
    }

    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---
    // -------___-_---_--_- DANGER ZONE --__---_--__- DANGER ZONE --- __-_--___-- DANGER ZONE ---__-___-------____---_---

    // Description of this crapcode currWindowLabels by indexes: 0 - topleft, 1 - topright, 2 - 2nd topright, 3 - bottom
    // !!! accessing without arraylist -> add +2 to every index
    // PS: DO NOT TOUCH, it works.. somehow..
    private void writeScore(PlayerEntity p) {
        ArrayList<Label> currentWindowLabels = new ArrayList<>();
        for (Node n : ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren()) {
            if (n instanceof Label) currentWindowLabels.add((Label) n);
        }

        FrameEntity currFrame = p.getFrame(gamePosition.get());

        if (currFrame.isStrike()) {
            if (!currFrame.isLastFrame()) currentWindowLabels.get(1).setText("X");
            else if (currFrame.getRollThree() != null) {
                if (currFrame.getRollThree() == 10) currentWindowLabels.get(1).setText("X");
                else currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
                currentWindowLabels.get(3).setText("");
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get() + 1)).getChildren().get(0)).setText(p.sumScore()
                        + "");
            } else if (currFrame.getRollTwo() != null) {
                if (currFrame.getRollTwo() == 10) currentWindowLabels.get(2).setText("X");
                else currentWindowLabels.get(2).setText("" + currFrame.getRollTwo());
            } else currentWindowLabels.get(0).setText("X");
        } else if (currFrame.isSpare()) {
            if (!currFrame.isLastFrame()) currentWindowLabels.get(1).setText("/");
            else if (currFrame.getRollThree() != null) {
                currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
                currentWindowLabels.get(3).setText("");
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get() + 1)).getChildren().get(0)).setText(p.sumScore()
                        + "");
            } else currentWindowLabels.get(2).setText("/");
        } else {
            if (currFrame.getRollTwo() != null && !currFrame.isLastFrame()) {
                currentWindowLabels.get(1).setText("" + currFrame.getRollTwo());
            } else if (currFrame.getRollTwo() != null) {
                currentWindowLabels.get(2).setText("" + currFrame.getRollTwo());
                currentWindowLabels.get(3).setText("");
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get() + 1)).getChildren().get(0)).setText(p.sumScore()
                        + "");
            } else currentWindowLabels.get(0).setText("" + currFrame.getRollOne());

            if (currFrame.getRollThree() != null) {
                currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
                currentWindowLabels.get(3).setText("");
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get() + 1)).getChildren().get(0)).setText(p.sumScore()
                        + "");
            }
        }

        for (int i = p.getFrames().size() - 1; i >= 0; i--) {
            if (p.getFrame(i).score() != null && ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(i)).getChildren().get(5)).getText()
                    .isEmpty()) {
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(i)).getChildren().get(5)).setText(p.sumScore(i) + "");
            }
        }

        if ((currFrame.isStrike() || currFrame.isSpare() || currFrame.getRollTwo() != null) && !currFrame.isLastFrame()) currentWindowLabels.get(3).setText("");
    }
}
