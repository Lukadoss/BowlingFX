package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.models.Game;
import es.ulpgc.bowling.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class GuiController {
    public TextArea outputArea;
    public TextField console;
    public Button butGames, butLead;
    private static Connection connection;
    public TableView mainTable;
    public Pane mainPane;

    //If you want to acces remote DB, use another thread. I am lazy and using local..
    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bowling.db");
            //connection = DriverManager.getConnection(Config.MYSQL_URL, Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could not connect to DB server. Error: \n" + e.getMessage());
            shutdown();
        }
        resizePanels();
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void resizePanels() {
        mainPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefWidth(mainPane.getLayoutBounds().getWidth());
            outputArea.setPrefWidth(mainPane.getLayoutBounds().getWidth());
        });

        mainPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefHeight(mainPane.getLayoutBounds().getHeight());
            outputArea.setPrefHeight(mainPane.getLayoutBounds().getHeight());
        });
    }

    public void getListOfGames(ActionEvent actionEvent) {
        ResultSet rs = sqlExec("SELECT game.title, SUM(player.top_score) as sum from game\n" +
                "LEFT JOIN player ON game.id = player.game_id\n" +
                "GROUP BY game.id ORDER BY sum DESC;");
        TableColumn<Game, String> game_title = new TableColumn<>("Game title");
        game_title.setMinWidth(100);
        game_title.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Game, String> best_score = new TableColumn<>("Achieved score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Player, String>, TableCell<Player, String>> cellFactory
                = //
                new Callback<TableColumn<Player, String>, TableCell<Player, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Player, String> param) {
                        final TableCell<Player, String> cell = new TableCell<Player, String>() {

                            final Button btn = new Button("Game details");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Player person = getTableView().getItems().get(getIndex());
                                        System.out.println(person.getName());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(game_title, best_score, actionCol);

        ArrayList<Game> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(new Game(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Game> data = FXCollections.observableList(list);
        mainTable.setItems(data);
    }

    public void getListOfLeaderboard(ActionEvent actionEvent) {
        ResultSet rs = sqlExec("SELECT * FROM player ORDER BY top_score DESC");

        TableColumn<Player, String> player_name = new TableColumn<>("Player name");
        player_name.setMinWidth(100);
        player_name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Player, String> best_score = new TableColumn<>("Best score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(
                new PropertyValueFactory<>("topScore"));

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(player_name, best_score);

        ArrayList<Player> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Player p = new Player(rs.getString("name"));
                p.setTopScore(rs.getInt("top_score"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Player> data = FXCollections.observableList(list);
        mainTable.setItems(data);
    }

    private String drawLine() {
        //Text t = new Text("-");
        //int x = (int) (outputArea.getWidth()/t.getLayoutBounds().getWidth());
        return new String(new char[50]).replace("\0", "-");
    }

    public void newGame(ActionEvent actionEvent) throws InterruptedException {
        console.clear();
        outputArea.clear();

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("newGame.fxml")));
            Stage stage = new Stage();
            stage.setTitle("New game");
            stage.setScene(new Scene(root, 300, 110));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainTable.setVisible(false);
        outputArea.setVisible(true);
    }


    private void out(String one) {
        outputArea.setText(one + "\n" + outputArea.getText());
    }

    private void formattedOutput(String one, String two) {
        out(String.format("%-34s%1s%15s", one,"|", two));
    }

    @FXML
    private void executeCommand(ActionEvent actionEvent) {
        if (!console.getText().isEmpty()) {
            switch (console.getText()) {
                case "clear":
                    outputArea.clear();
                    break;
            }
        }
        console.clear();
    }

    private void shutdown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public static ResultSet sqlExec(String sql) {
        ResultSet rs = null;
        try {
            Statement stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
