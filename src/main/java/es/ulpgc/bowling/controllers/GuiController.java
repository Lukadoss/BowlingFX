package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.javafx.Game;
import es.ulpgc.bowling.GameState;
import es.ulpgc.bowling.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Component;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@Component
public class GuiController {
    public TextArea outputArea;
    public TextField console;
    public Button butGames, butLead, butNewGame;
    public TableView mainTable;
    public Pane mainPane;


    private static Connection connection;
    private static GameState gameState = GameState.IN_LOBBY;
    private ArrayList<NewGameController> ngcList;
    private int currentGameId = 0;
    //If you want to acces remote DB, use another thread. I am lazy and using local..
    public void initialize() {
        ngcList = new ArrayList<>();
//        try {
//            connection = DriverManager.getConnection("jdbc:sqlite:bowling.db");
//            //connection = DriverManager.getConnection(Config.MYSQL_URL, Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
//        } catch (SQLException e) {
//            System.out.println("Could not connect to DB server. Error: \n" + e.getMessage());
//            shutdown();
//        }
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
        ResultSet rs = sqlExec("SELECT game.title, SUM(player.total_score) as sum, game.id from game\n" +
                "LEFT JOIN player ON game.id = player.game_id\n" +
                "GROUP BY game.id ORDER BY sum DESC;");
        TableColumn<Game, String> game_title = new TableColumn<>("Game title");
        game_title.setMinWidth(100);
        game_title.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Game, String> best_score = new TableColumn<>("Achieved score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn actionCol = new TableColumn("Game details");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Game, String>, TableCell<Game, String>> cellFactory
                = //
                new Callback<TableColumn<Game, String>, TableCell<Game, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Game, String> param) {
                        final TableCell<Game, String> cell = new TableCell<Game, String>() {

                            final Button btn = new Button("Statistics");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> showGameStats(getTableView().getItems().get(getIndex()).getId()));
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }

                    private void showGameStats(int id) {
                        FXMLLoader root;
                        try {
                            root = new FXMLLoader(getClass().getClassLoader().getResource(("gameStats.fxml")));
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Game statistics");
                            stage.setScene(new Scene(root.load()));
                            stage.setResizable(false);
                            stage.show();
                            root.<GameStatsController>getController().initGame(id);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
        actionCol.setStyle("-fx-aligment: CENTER-RIGHT");
        actionCol.setCellFactory(cellFactory);
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(game_title, best_score, actionCol);

        ArrayList<Game> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Game g = new Game(rs.getString(1));
                g.setScore(rs.getInt(2));
                g.setId(rs.getInt(3));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Game> data = FXCollections.observableList(list);
        mainTable.setItems(data);
    }

    public void getListOfLeaderboard(ActionEvent actionEvent) {
        ResultSet rs = sqlExec("SELECT * FROM player ORDER BY total_score DESC");

        TableColumn<Player, String> player_name = new TableColumn<>("Player name");
        player_name.setMinWidth(100);
        player_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> best_score = new TableColumn<>("Best score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(player_name, best_score);

        ArrayList<Player> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Player p = new Player(rs.getString("name"));
                p.setTotalScore(rs.getInt("total_score"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Player> data = FXCollections.observableList(list);
        mainTable.setItems(data);
    }

    public void newGame(ActionEvent actionEvent) throws InterruptedException {
        console.clear();
        outputArea.clear();
        setGameState(GameState.STARTING);
        butNewGame.setDisable(true);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("newGame.fxml"));
            Parent root = loader.load();
            ngcList.add(loader.getController());
            ngcList.get(ngcList.size()-1).setGuiController(this);

            Stage stage = new Stage();
            stage.setTitle("New game");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.setOnHidden(e->butNewGame.setDisable(false));
        } catch (IOException e) {
            e.printStackTrace();
            butNewGame.setDisable(true);
            setGameState(GameState.IN_LOBBY);
        }
    }

    private void out(String one) {
        outputArea.setText(one + "\n" + outputArea.getText());
    }

    private void formattedOutput(String one, String two) {
        out(String.format("%-34s%1s%15s", one, "|", two));
    }

    private String drawLine() {
        //Text t = new Text("-");
        //int x = (int) (outputArea.getWidth()/t.getLayoutBounds().getWidth());
        return new String(new char[50]).replace("\0", "-");
    }

    @FXML
    private void executeCommand(ActionEvent actionEvent) {
        if (!console.getText().isEmpty()) {
            out("\n");
            switch (console.getText()) {
                case "help":
                    out("Commands:\nclear - clears the output area\nstatus - Returns current game status\nplayers - shows all players in game\ngame - returns" +
                            " sum of current ingame score");
                    break;
                case "h":
                    out("Commands:\nclear - clears the output area\nstatus - Returns current game status\nplayers - shows all players in game\ngame - returns" +
                            " sum of current ingame score");
                    break;
                case "clear":
                    outputArea.clear();
                    break;
                case "status":
                    out("Game status: "+gameState.name());
                    for(NewGameController c : ngcList) out(c.getGame().getId()+") "+c.getGame().getName());
                    out("Number of games: "+ngcList.size());
                    break;
                case "players":
                    for(Player p : ngcList.get(currentGameId).getPlayers()) out(p.getName());
                    out("Currently playing players:");
                    break;
                case "game":
                    out("Current score for game \""+ngcList.get(currentGameId).getGame().getName()+"\" is "+ngcList.get(currentGameId).getGame().getScore());
                    break;
                default:
                    out("Command not found! ---Write \"help\" to see all commands---");
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

    static ResultSet sqlExec(String sql) {
        ResultSet rs = null;
        try {
            Statement stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void setGameState(GameState g){
        gameState = g;
    }

    public void newGameGui(){
        mainTable.setVisible(false);
        outputArea.setVisible(true);
        butLead.setDisable(true);
        butGames.setDisable(true);
        console.setDisable(false);
    }
}
