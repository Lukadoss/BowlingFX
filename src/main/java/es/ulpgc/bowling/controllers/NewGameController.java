package es.ulpgc.bowling.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewGameController {
    public Label mainLabel;
    public TextField textField;
    public Button nextButton;
    public Button b1, b2, b3, b4, b5, b6, b7, b8;
    public Group buttonGroup;

    private String gameName;
    private int numOfPlayers;

    public void cancel(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void loadPlayersLayers(ActionEvent actionEvent) {
        if (textField.getText().isEmpty()) gameName = "Unknown game";
        else gameName = textField.getText();
        mainLabel.setText("Choose the number of players:");
        textField.setVisible(false);
        nextButton.setVisible(false);
        buttonGroup.setVisible(true);

    }

    public void checkKey(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            loadPlayersLayers(new ActionEvent());
        }
    }

    public void getPressedButton(ActionEvent actionEvent) {
        numOfPlayers = Integer.parseInt(actionEvent.getSource().toString().split("'")[0]);
    }
}
