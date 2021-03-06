package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.LineEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Controller for playing game
 *
 * @author Petr Lukasik
 */
@Component
public class NewGameController {
    /**
     * Label for name
     */
    public Label mainLabel;

    /**
     * Main textfield for gamename
     */
    public TextField textField;

    /**
     * Button control elements
     */
    public Button nextButton, b1, b2, b3, b4, b5, b6, b7, b8;

    /**
     * Group of player number buttons
     */
    public Group buttonGroup;

    /**
     * Main container
     */
    public AnchorPane anchorPane;

    /**
     * Temporary help boolean
     */
    private boolean playersLoad = true;
    /**
     * New game name
     */
    private String gameName;

    /**
     * Textfields of name of players
     */
    private HashMap<Label, TextField> nameFields;

    /**
     * Array of player names
     */
    private ArrayList<String> names;

    /**
     * Gui controller
     */
    private GuiController gc;

    private GameEntity game;
    private LineEntity line;

    public void initialize() {
        nameFields = new HashMap<>();
        names = new ArrayList<>();
    }

    /**
     * Method executed when cancel button is pressed. Returns main window to previsous state and hides new game window
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        gc.backButton.setDisable(false);
        gc.leaderboardsButton.setDisable(false);
        gc.topGamesButton.setDisable(false);
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    /**
     * Next button pressed, event depends on current state
     * @param actionEvent
     */
    public void next(ActionEvent actionEvent) {
        if (playersLoad) {
            playersLoad = false;
            loadPlayersLayer();
        } else {
            names.clear();
            AtomicBoolean isOkay = new AtomicBoolean(true);
            nameFields.forEach((k, v) -> {
                if (v.getText().isEmpty()) {
                    isOkay.set(false);
                    k.setStyle("-fx-text-fill: #ff0000");
                } else {
                    names.add(v.getText());
                    k.setStyle("-fx-text-fill: #000000");
                }
            });
            if (isOkay.get()) {
                ArrayList<PlayerEntity> players = new ArrayList<>();
                names.forEach(e -> players.add(new PlayerEntity(e)));

                game = new GameEntity(gameName, players);
                game.setLine(line);
                game = gc.getGameRepo().save(game);

                gc.mainLabel.setText(gameName);
                gc.setCurrentGame(game);
                gc.newGameGui();

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        }
    }

    /**
     * Prepares next window with choosing number of player
     */
    private void loadPlayersLayer() {
        if (textField.getText().isEmpty()) {
            gameName = "Unknown game #" + gc.getGameRepo().count();
        } else gameName = textField.getText();
        mainLabel.setText("Choose the number of players");
        textField.setVisible(false);
        nextButton.setVisible(false);
        buttonGroup.setVisible(true);
    }

    /**
     * Method defining pressed number of players
     * @param actionEvent
     */
    public void pressNumOfPlayers(ActionEvent actionEvent) {
        int numOfPlayers = Integer.parseInt(actionEvent.getSource().toString().split("'")[1]);
        mainLabel.setText("Create or choose players");
        buttonGroup.setVisible(false);
        nextButton.setVisible(true);

        ((Node) (actionEvent.getSource())).getScene().getWindow().setHeight(((Node) (actionEvent.getSource())).getScene
                ().getWindow().getHeight() + 50 * (numOfPlayers - 1));
        for (int i = 1; i <= numOfPlayers; i++) {
            Label l = new Label("Player " + i);
            l.setLayoutX(15);
            l.setLayoutY(50 * i - 18);

            TextField t = new TextField();
            t.setEditable(true);
            t.setLayoutY(50 * i);
            t.setLayoutX(15);
            t.setPrefHeight(25);
            t.setPrefWidth(240);
            t.setId(i + "");

            nameFields.put(l, t);
            anchorPane.getChildren().addAll(l, t);
        }
    }

    /**
     * Checking key input, on enter move next
     * @param keyEvent
     */
    public void checkKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            next(new ActionEvent());
        }
    }

    /*
     *  Getters and setters
     */

    public void setUpGC(GuiController gc) {
        this.gc = gc;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setUpLine(LineEntity line) {
        this.line = line;
    }
}
