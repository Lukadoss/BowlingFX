package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.entity.BowlingEntity;
import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.LineEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import es.ulpgc.bowling.repository.BowlingRepository;
import es.ulpgc.bowling.repository.GameRepository;
import es.ulpgc.bowling.repository.LineRepository;
import es.ulpgc.bowling.repository.PlayerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiController {
    public TextArea outputArea;
    public TextField console;
    public TableView mainTable;
    public Pane mainPane;
    public Label mainLabel;
    public GridPane bowlingGridPane;
    public Circle c1, c2;
    public Button backButton;

    @Autowired
    private BowlingRepository bowlRepo;

    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private GameRepository gameRepo;

    private BowlingEntity currentBowling;

    private GameEntity currentGame;

    public void initialize() {
        resizePanels();
    }

    public void getListOfGames(ActionEvent actionEvent) {
        changeWindowItems(true);

        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<GameEntity, String> game_title = new TableColumn<>("Game title");
        game_title.setMinWidth(100);
        game_title.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<GameEntity, Integer> best_score = new TableColumn<>("Achieved score");
        best_score.setMinWidth(50);
        best_score.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        TableColumn<GameEntity, String> duration = new TableColumn<>("Game duration");
        duration.setCellValueFactory(new PropertyValueFactory<>("gameDuration"));

        TableColumn actionCol = new TableColumn("Game details");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<GameEntity, String>, TableCell<GameEntity, String>> cellFactory
                = //
                new Callback<TableColumn<GameEntity, String>, TableCell<GameEntity, String>>() {
                    @Override
                    public TableCell call(final TableColumn<GameEntity, String> param) {
                        final TableCell<GameEntity, String> cell = new TableCell<GameEntity, String>() {

                            final Button btn = new Button("Statistics");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> showGameStats(getTableView().getItems().get(getIndex())));
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }

                    private void showGameStats(GameEntity game) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource(("gameStats.fxml")));
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Game statistics");
                            stage.setScene(new Scene(root.load()));
                            stage.setResizable(false);
                            stage.show();
                            root.<GameStatsController>getController().initGame(game);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
        actionCol.setStyle("-fx-aligment: CENTER");
        actionCol.setCellFactory(cellFactory);
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(game_title, best_score, duration, actionCol);

        ObservableList<GameEntity> data = FXCollections.observableList(gameRepo.findAllByIdGreaterThanEqualOrderByTotalScoreDesc(0));
        mainTable.setItems(data);
    }

    public void getListOfLeaderboard(ActionEvent actionEvent) {
        changeWindowItems(true);

        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PlayerEntity, String> player_name = new TableColumn<>("Player name");
        player_name.setMinWidth(100);
        player_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PlayerEntity, String> best_score = new TableColumn<>("Best score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(new PropertyValueFactory<>("maxScore"));

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(player_name, best_score);

        ObservableList<PlayerEntity> data = FXCollections.observableList(playerRepo.findByIdGreaterThanEqualOrderByMaxScoreDesc(0));
        mainTable.setItems(data);
    }

    public void getClickedBowlingBar(MouseEvent mouseEvent) {
        switch (((Circle) mouseEvent.getSource()).getId()) {
            case "c1":
                currentBowling = bowlRepo.findById(1).get();
                mainLabel.setText(currentBowling.getName());
                break;
            case "c2":
                currentBowling = bowlRepo.findById(2).get();
                mainLabel.setText(currentBowling.getName());
                break;
            default:
                break;
        }

        Callback<TableColumn<LineEntity, Object>, TableCell<LineEntity, Object>> cellFactory = new Callback<TableColumn<LineEntity, Object>,
                TableCell<LineEntity, Object>>() {
            @Override
            public TableCell call(final TableColumn<LineEntity, Object> param) {
                final TableCell<LineEntity, Object> cell = new TableCell<LineEntity, Object>() {

                    final Button btn = new Button("Start new game");

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else if (item instanceof String) {
                            setText((String) item);
                        } else {
                            btn.setOnAction(event -> newGame(getTableView().getItems().get(getIndex())));
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }


        };

        mainTable.getColumns().clear();

        TableColumn<LineEntity, Integer> lineID = new TableColumn<>("Line");
        lineID.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.1));
        lineID.setResizable(false);
        lineID.setCellValueFactory(new PropertyValueFactory<>("id"));
        lineID.setSortable(false);

        TableColumn<LineEntity, Object> lineName = new TableColumn<>("Current game");
        lineName.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.9));
        lineName.setResizable(false);
        lineName.setCellValueFactory(new PropertyValueFactory<>("runningGameName"));
        lineName.setCellFactory(cellFactory);
        lineName.setStyle("-fx-alignment: CENTER");
        lineName.setSortable(false);

        mainTable.getColumns().addAll(lineID, lineName);

        ObservableList<LineEntity> data = FXCollections.observableList(lineRepo.findByBowlingId(currentBowling.getId()));
        mainTable.setItems(data);

        changeWindowItems(true);
    }

    public void newGame(LineEntity line) {
        console.clear();
        outputArea.clear();
        backButton.setDisable(true);

        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource(("/newGame.fxml")));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("New game");
            stage.setScene(new Scene(root.load()));
            stage.setResizable(false);
            stage.show();
            root.<NewGameController>getController().setUpGC(this);
            root.<NewGameController>getController().setUpLine(line);
        } catch (Exception e) {
            e.printStackTrace();
            backButton.setDisable(false);
            changeWindowItems(false);
        }
    }

    private void changeWindowItems(boolean next) {
        if (next) {
            bowlingGridPane.setVisible(false);
            mainTable.setVisible(true);
            backButton.setDisable(false);
        } else {
            bowlingGridPane.setVisible(true);
            mainTable.setVisible(false);
            backButton.setDisable(true);
            mainLabel.setText("Bowling bars");
        }
    }

    public void newGameGui() {
        mainTable.setVisible(false);
        outputArea.setVisible(true);
        backButton.setDisable(true);
        console.setVisible(true);
    }

    private void resizePanels() {
        mainPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefWidth(mainPane.getLayoutBounds().getWidth());
            outputArea.setPrefWidth(mainPane.getLayoutBounds().getWidth());
            bowlingGridPane.setPrefWidth(mainPane.getLayoutBounds().getWidth());
        });

        mainPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefHeight(mainPane.getLayoutBounds().getHeight());
            outputArea.setPrefHeight(mainPane.getLayoutBounds().getHeight());
            bowlingGridPane.setPrefHeight(mainPane.getLayoutBounds().getHeight());
        });


    }

    public void goBack(ActionEvent actionEvent) {
        changeWindowItems(false);
    }

    private void out(String one) {
        outputArea.setText(one + "\n" + outputArea.getText());
    }

    private String drawLine() {
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
                    out("game_id="+currentGame.getId()+", name="+currentGame.getName()+", running="+currentGame.isRunning());
                    break;
                case "players":
                    out(currentGame.getPlayers().toString());
                    out("Currently playing players:");
                    break;
                case "game":
                    out("Current score for game \""+currentGame.getName()+"\" is "+currentGame.getTotalScore());
                    break;
                default:
                    out("Command not found! ---Write \"help\" to see all commands---");
            }
        }
        console.clear();
    }

    public GameRepository getGameRepo() {
        return gameRepo;
    }

    public BowlingRepository getBowlRepo() {
        return bowlRepo;
    }

    public LineRepository getLineRepo() {
        return lineRepo;
    }

    public PlayerRepository getPlayerRepo() {
        return playerRepo;
    }

    public void setCurrentGame(GameEntity currentGame) {
        this.currentGame = currentGame;
    }
}
