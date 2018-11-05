package es.ulpgc.bowling.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

public class GuiController {
    public TextArea outputArea;
    public TextField console;
    public Button butGames, butLead;
    private int numOfPlayers = 0;
    private Connection connection;

    //If you want to acces remote DB, use another thread. I am lazy and using local..
    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bowling.db");
            //connection = DriverManager.getConnection(Config.MYSQL_URL, Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could not connect to DB server. Error: " + e.getMessage());
            shutdown();
        }
    }

    private boolean checkInput(int x) {
        if (x <= 8 && x > 0) {
            numOfPlayers = x;
            return false;
        } else {
            System.out.println("Wrong input! Number of players must be between 1-8");
            return true;
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
        outputArea.clear();
        butGames.setDisable(true);
        butLead.setDisable(true);

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
}
