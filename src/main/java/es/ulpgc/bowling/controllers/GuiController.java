package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class GuiController {
    public TextArea outputArea;
    public TextField console;
    public Button butGames, butLead;
    private boolean newGame;
    private int numOfPlayers;
    private Connection connection;

    //If you want to acces remote DB, use another thread. I am lazy and using local..
    public void initialize() {
        newGame = false;
        numOfPlayers = 0;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bowling.db");
            //connection = DriverManager.getConnection(Config.MYSQL_URL, Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could not connect to DB server. Error: \n" + e.getMessage());
            shutdown();
        }
    }

    public void getListOfGames(ActionEvent actionEvent) {
        outputArea.clear();
        out("\n");
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select title from game")
        ) {
            while (rs.next()) {
                String name = rs.getString("title");
                out(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out("-----------------------------------");
        out("Game title");
    }

    public void getListOfLeaderboard(ActionEvent actionEvent) {
        outputArea.clear();
        out("\n");
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from player ORDER BY top_score ASC")
        ) {
            while (rs.next()) {
                String name = rs.getString("name");
                int topscore = rs.getInt("top_score");
                formattedOutput(name, String.valueOf(topscore));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out("-----------------------------------");
        formattedOutput("Name", "Top score");

    }

    public void newGame(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("newGame.fxml")));
            Stage stage = new Stage();
            stage.setTitle("New game");
            stage.setScene(new Scene(root, 300, 110));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void out(String one) {
        outputArea.setText(one+"\n"+outputArea.getText());
    }

    private void formattedOutput(String one, String two) {
        out(String.format("%-15s%-30s", one, two));
    }

    @FXML
    private void executeCommand(ActionEvent actionEvent) {
        if (!console.getText().isEmpty()){
            if (newGame){
                new Game(console.getText(),0);
            }
            switch(console.getText()){
                case "clear":
                    outputArea.clear();
                    break;
            }
        }
        console.clear();
    }

    private void shutdown(){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void getInput(KeyEvent keyEvent) {
        if (newGame){
            outputArea.setText("Write the name of the game: "+console.getText());
        }
    }
}
