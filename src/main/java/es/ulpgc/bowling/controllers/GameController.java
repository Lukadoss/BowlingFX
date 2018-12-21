package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.BowlingApplication;
import es.ulpgc.bowling.config.Color;
import es.ulpgc.bowling.entity.FrameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GameController {

    private GuiController gc;
    private ArrayList<HBox> playerBoxes;
    private Label rollOut, finalScore;
    private AtomicInteger gamePosition;
    private Random r;
    private int decay, tmp, playerCounter;
    private HBox rollBox;
    private ArrayList<Button> rollButts;

    public GameController(){}

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

                if (j == 0)
                    ap.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 2; -fx-background-radius: 5; -fx-border-radius: 2; " +
                            "-fx-background-color: " + Color.values()[i].getColor() + ";");
                else if (j == 10)
                    ap.setStyle("-fx-border-color: black; -fx-border-width: 2 2 2 1; -fx-background-radius: 5; -fx-border-radius: 2; " +
                            "-fx-background-color: white;");
                else
                    ap.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-background-radius: 5; -fx-border-radius: 2; -fx-background-color: " +
                            Color.values()[i].getColor() + ";");

                prepareLabels(ap, j);
                HBox.setHgrow(ap, Priority.ALWAYS);
                h.getChildren().add(ap);
            }


            gc.gameVBox.getChildren().addAll(l, h);
            playerBoxes.add(h);
        }

        Pane filler = new Pane();
        Pane filler2 = new Pane();
        VBox.setVgrow(filler, Priority.ALWAYS);
        HBox.setHgrow(filler2, Priority.ALWAYS);

        rollOut = new Label();
        rollOut.setStyle("-fx-font: bold 22px System");
        rollOut.setAlignment(Pos.CENTER);
        rollOut.setPadding(new Insets(2, 2, 2, 2));

        Button rollBut = new Button("Random roll");
        rollBut.setOnAction(e -> makeRoll(-1));

        FlowPane rollPn = new FlowPane();
        rollPn.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 5");
        rollPn.setAlignment(Pos.CENTER);
        rollPn.getChildren().add(rollOut);
        rollPn.setPrefSize(50, 50);

        rollBox = new HBox();
        rollBox.setStyle("-fx-border-color: #cbcbcb");
        rollBox.setPadding(new Insets(5, 5, 5, 5));
        rollBox.setAlignment(Pos.CENTER_LEFT);
        rollBox.setFillHeight(true);

        rollButts = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            Button b = new Button(String.valueOf(i));
            b.setPrefSize(30, 30);
            int x = i;
            b.setOnAction(e -> makeRoll(x));
            HBox.setMargin(b, new Insets(0, 0, 0, 5));
            rollButts.add(b);
        }
        Separator s = new Separator();
        s.setOrientation(Orientation.VERTICAL);
        HBox.setMargin(s, new Insets(0, 5, 0, 10));

        rollBox.getChildren().addAll(rollBut, s);
        rollBox.getChildren().addAll(rollButts);
        rollBox.getChildren().addAll(filler2, rollPn);


        HBox finalScoreBox = new HBox();
        VBox.setMargin(finalScoreBox, new Insets(20, 0, 0, 0));

        for (int j = 0; j < 10; j++) {
            AnchorPane ap = new AnchorPane();
            ap.setMinSize(55, 50);
            HBox.setHgrow(ap, Priority.ALWAYS);
            finalScoreBox.getChildren().add(ap);
        }
        AnchorPane ap = new AnchorPane();
        ap.setMinSize(55, 50);
        ap.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-radius: 5; -fx-border-radius: 2; " + "-fx-background-color: rgba(255,255," +
                "255,0.82);");
        HBox.setHgrow(ap, Priority.ALWAYS);

        finalScore = new Label("");
        finalScore.setAlignment(Pos.CENTER);
        finalScore.setStyle("-fx-font: bold 18px System; -fx-text-alignment: center");

        AnchorPane.setBottomAnchor(finalScore, 0D);
        AnchorPane.setLeftAnchor(finalScore, 0D);
        AnchorPane.setRightAnchor(finalScore, 0D);
        AnchorPane.setTopAnchor(finalScore, 0D);
        ap.getChildren().add(finalScore);

        finalScoreBox.getChildren().add(ap);

        gc.gameVBox.getChildren().addAll(finalScoreBox, filler, rollBox);
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

        setUpClock(startTime);
        gc.clock.setCycleCount(Animation.INDEFINITE);
        gc.clock.play();

        BowlingApplication.getPrimaryStage().setMinWidth(750);
        BowlingApplication.getPrimaryStage().setMinHeight(100);
        for (Node n : gc.gameVBox.getChildren()) {
            if (n instanceof HBox) BowlingApplication.getPrimaryStage().setMinHeight(BowlingApplication.getPrimaryStage().getMinHeight() + 75);
        }
    }

    public void loadGame() {
        gamePosition = new AtomicInteger();
        LocalDateTime startTime;

        if (gc.getCurrentGame().getStarted() != null) startTime = gc.getCurrentGame().getStarted();
        else startTime = LocalDateTime.now();

        setUpClock(startTime);
        gc.clock.setCycleCount(Animation.INDEFINITE);
        gc.clock.play();

        BowlingApplication.getPrimaryStage().setMinWidth(750);
        BowlingApplication.getPrimaryStage().setMinHeight(100);
        for (Node n : gc.gameVBox.getChildren()) {
            if (n instanceof HBox)
                BowlingApplication.getPrimaryStage().setMinHeight(BowlingApplication.getPrimaryStage().getMinHeight() + 75);
        }

        List<FrameEntity> tmpList = new ArrayList<>();
        int counter = 0;
        while (gc.getCurrentGame().getPlayers().get(playerModulo()).getFrame(counter)!=null) {
            tmpList.add(gc.getCurrentGame().getPlayers().get(playerModulo()).getFrame(counter));
            playerCounter++;
            if (playerModulo()==0) counter++;
        }
        for (PlayerEntity p : gc.getCurrentGame().getPlayers()){
            p.clearEntity();
            gc.getFrameRepo().deleteAllByPlayerId(p.getId());
        }

        for (FrameEntity frame : tmpList) {
            if (frame.getRollOne()!=null) makeRoll(frame.getRollOne());
            if (frame.getRollTwo()!=null) makeRoll(frame.getRollTwo());
            if (frame.getRollThree()!=null) makeRoll(frame.getRollThree());
        }
        playerCounter=0;

        ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");
    }

    private void setUpClock(LocalDateTime startTime) {
        gc.clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            java.time.Duration duration = java.time.Duration.between(startTime, LocalDateTime.now());
            long seconds = duration.getSeconds();
            int hours = (int) (seconds / 3600);
            int minutes = (int) ((seconds % 3600) / 60);
            int secs = (int) (seconds % 60);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            gc.gameTime.setText(LocalDateTime.of(1, 1, 1, hours, minutes, secs).format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
    }

    public void endGame() {
        rollBox.setDisable(true);
        rollOut.setText("Game ended!");
        gc.getGameRepo().save(gc.getCurrentGame());
        gc.clock.stop();
        BowlingApplication.getPrimaryStage().setMinWidth(600);
        BowlingApplication.getPrimaryStage().setMinHeight(400);
    }

    private int playerModulo() {
        return playerCounter % gc.getCurrentGame().getPlayers().size();
    }

    private void makeRoll(int roll) {
        PlayerEntity p = gc.getCurrentGame().getPlayers().get(playerModulo());

        if (roll == -1) tmp = r.nextInt(decay);
        else tmp = roll;

        p.roll(tmp);

        rollOut.setText(String.valueOf(tmp));

        writeScore();

        PlayerEntity lastPlayer = gc.getCurrentGame().getPlayers().get(gc.getCurrentGame().getPlayers().size() - 1);
        if (lastPlayer.getFrames().size() != 0 && lastPlayer.getFrameOnIndex(lastPlayer.getFrames().size() - 1).isLastFrame() && lastPlayer.getFrameOnIndex(lastPlayer
                .getFrames().size() - 1).score() != null) {
            gc.getCurrentGame().endGame();
            endGame();
            return;
        }

        if (tmp != 10 && decay == 11) {
            if ((p.getFrameOnIndex(gamePosition.get()) != null && p.getFrameOnIndex(gamePosition.get()).getRollTwo() != null)) {
                if (p.getFrame(gamePosition.get()).isLastFrame() && p.getFrame(gamePosition.get()).getRollThree() == null) decay -= tmp;

                if (!(p.getFrameOnIndex(gamePosition.get()).isLastFrame() && (p.getFrameOnIndex(gamePosition.get()).isSpare() || p.getFrameOnIndex(gamePosition.get()).isStrike())
                ) || p.getFrameOnIndex(gamePosition.get()).getRollThree() != null) playerCounter++;
                    decay = 11;
                    playerCounter++;
                }

                if (playerModulo() != 0)
                    ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");
                else if (!p.getFrameOnIndex(gamePosition.get()).isLastFrame())
                    ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().
                            get(gamePosition.get() + 1)).getChildren().get(5)).setText("> > >");

                if (!p.getFrameOnIndex(gamePosition.get()).isLastFrame() && playerModulo() == 0) {
                    gamePosition.getAndIncrement();
                }
            } else decay -= tmp;
        } else {
            decay = 11;
            if (!(p.getFrameOnIndex(gamePosition.get()).isLastFrame() && (p.getFrameOnIndex(gamePosition.get()).isSpare() || p.getFrameOnIndex(gamePosition.get()).isStrike())) ||
                    p.getFrameOnIndex(gamePosition.get()).getRollThree() != null) playerCounter++;

            if (playerModulo() != 0)
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren().get(5)).setText("> > >");
            else if (!p.getFrameOnIndex(gamePosition.get()).isLastFrame())
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().
                        get(gamePosition.get() + 1)).getChildren().get(5)).setText("> > >");

            if (!p.getFrameOnIndex(gamePosition.get()).isLastFrame() && playerModulo() == 0) {
                gamePosition.getAndIncrement();
            }
        }

        rollButts.forEach(e -> e.setDisable(false));

        for (int i = decay; i <= 10; i++) {
            rollButts.get(i).setDisable(true);
        }
    }

    private void writeScore() {
        ArrayList<Label> currentWindowLabels = new ArrayList<>();
        for (Node n : ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(gamePosition.get())).getChildren()) {
            if (n instanceof Label) currentWindowLabels.add((Label) n);
        }

        FrameEntity currFrame = gc.getCurrentGame().getPlayers().get(playerModulo()).getFrame(gamePosition.get());

        if (currFrame.isStrike()) {
            if (!currFrame.isLastFrame()) {
                currentWindowLabels.get(1).setText("X");
                currentWindowLabels.get(3).setText("");
            }
            else if (currFrame.getRollThree() != null) {
                if (currFrame.getRollThree() == 10) currentWindowLabels.get(1).setText("X");
                else currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
            } else if (currFrame.getRollTwo() != null) {
                if (currFrame.getRollTwo() == 10) currentWindowLabels.get(2).setText("X");
                else currentWindowLabels.get(2).setText("" + currFrame.getRollTwo());
            } else currentWindowLabels.get(0).setText("X");
        } else if (currFrame.isSpare()) {
            if (!currFrame.isLastFrame()) {
                currentWindowLabels.get(1).setText("/");
                currentWindowLabels.get(3).setText("");
            }
            else if (currFrame.getRollThree() != null) {
                if (currFrame.getRollThree() == 10) currentWindowLabels.get(1).setText("X");
                else currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
            } else currentWindowLabels.get(2).setText("/");
        } else {
            if (currFrame.getRollTwo() != null) {
                if (currFrame.isLastFrame()) currentWindowLabels.get(2).setText("" + currFrame.getRollTwo());
                else currentWindowLabels.get(1).setText("" + currFrame.getRollTwo());
            }
            else currentWindowLabels.get(0).setText("" + currFrame.getRollOne());

            if (currFrame.getRollThree() != null) currentWindowLabels.get(1).setText("" + currFrame.getRollThree());
        }

        for (int i = gc.getCurrentGame().getPlayers().get(playerModulo()).getFrames().size() - 1; i >= 0; i--) {
            if (gc.getCurrentGame().getPlayers().get(playerModulo()).getFrame(i).score() != null) {
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(i)).getChildren().get(5)).setText(gc.getCurrentGame().getPlayers()
                        .get(playerModulo()).sumScore(i) + "");
                ((Label) ((AnchorPane) playerBoxes.get(playerModulo()).getChildren().get(10)).getChildren().get(0)).setText(gc.getCurrentGame().getPlayers()
                        .get(playerModulo()).sumScore() + "");
                finalScore.setText(gc.getCurrentGame().getTotalScore() + "");
            }
        }

    }
}
