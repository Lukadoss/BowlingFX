package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.GameState;
import es.ulpgc.bowling.models.Game;
import es.ulpgc.bowling.models.Player;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewGameController {
    public Label mainLabel;
    public TextField textField;
    public Button nextButton;
    public Button b1, b2, b3, b4, b5, b6, b7, b8;
    public Group buttonGroup;
    public AnchorPane anchorPane;

    private boolean playersLoad = true;
    private String gameName;
    private int numOfPlayers;
    private HashMap<Label, TextField> comboList;
    private ArrayList<String> names;
    private ArrayList<Player> players;
    private Game game;
    private GuiController gc = null;

    public void initialize() {
        comboList = new HashMap<>();
        names = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void cancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void next(ActionEvent actionEvent) {
        if (playersLoad) {
            playersLoad = false;
            loadPlayersLayer();
        } else {
            names.clear();
            AtomicBoolean isOkay = new AtomicBoolean(true);
            comboList.forEach((k, v) -> {
                if (v.getText().isEmpty()) {
                    isOkay.set(false);
                    k.setStyle("-fx-text-fill: #ff0000");
                } else {
                    names.add(v.getText());
                    k.setStyle("-fx-text-fill: #000000");
                }
            });
            if (isOkay.get()) {
                game = new Game(gameName);
                names.forEach(e -> players.add(new Player(e)));
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                gc.setGameState(GameState.RUNNING);
                gc.newGameGui();
            }
        }
    }

    private void loadPlayersLayer() {
        if (textField.getText().isEmpty()) {
            ResultSet rs = GuiController.sqlExec("select COUNT(id) AS rowcount from game");
            try {
                rs.next();
                gameName = "Unknown game #" + rs.getInt(1);
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else gameName = textField.getText();
        mainLabel.setText("Choose the number of players");
        textField.setVisible(false);
        nextButton.setVisible(false);
        buttonGroup.setVisible(true);
    }

    public void checkKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            next(new ActionEvent());
        }
    }

    public void pressNumOfPlayers(ActionEvent actionEvent) {
        numOfPlayers = Integer.parseInt(actionEvent.getSource().toString().split("'")[1]);
//        ArrayList<String> list = new ArrayList<>();
//        ResultSet rs = GuiController.sqlExec("select id, name from player");
//        try {
//            while (rs.next()) {
//                list.add(rs.getString(2) + " (" + rs.getString(1) + ")");
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

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
//            ComboBox t = new ComboBox();
            t.setEditable(true);
//            t.setVisibleRowCount(5);
            t.setLayoutY(50 * i);
            t.setLayoutX(15);
            t.setPrefHeight(25);
            t.setPrefWidth(240);
            t.setId(i + "");
//            t.setItems(FXCollections.observableArrayList(list));
            comboList.put(l, t);

            anchorPane.getChildren().addAll(l, t);
        }
    }

    public Game getGame(){
        return game;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setGuiController(GuiController guiController) {
        this.gc = guiController;
    }
}
