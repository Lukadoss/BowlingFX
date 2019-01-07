package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.config.Color;
import es.ulpgc.bowling.entity.BowlingEntity;
import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.LineEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import es.ulpgc.bowling.repository.*;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Main controller for GUI of the application
 *
 * @author Petr Lukasik
 */
@Component
public class GuiController {
    /**
     * Table for displaying main bowling entities
     */
    public TableView mainTable;
    /**
     * Main container for every main panel display
     */
    public Pane mainPane;
    /**
     * Labels for names and time
     */
    public Label mainLabel, gameTime;
    /**
     * Container for bowling pubs
     */
    public GridPane bowlingGridPane;
    /**
     * Graphics behind bowling pubs
     */
    public Circle c1, c2;
    /**
     * Buttons in the main settings
     */
    public Button backButton, topGamesButton, leaderboardsButton;
    /**
     * Container for running game
     */
    public VBox gameVBox;

    /**
     * Connected bowling repository
     */
    @Autowired
    private BowlingRepository bowlRepo;

    /**
     * Connected line repository
     */
    @Autowired
    private LineRepository lineRepo;

    /**
     * Connected player repository
     */
    @Autowired
    private PlayerRepository playerRepo;

    /**
     * Connected game repository
     */
    @Autowired
    private GameRepository gameRepo;

    /**
     * Connected frame repository
     */
    @Autowired
    private FrameRepository frameRepo;

    /**
     * JavaFX animation for displaying game time
     */
    Timeline clock;

    /**
     * Entity of current opened bowling pub
     */
    private BowlingEntity currentBowling;

    /**
     * Entity of current opened game
     */
    private GameEntity currentGame;

    /**
     * Controller of current running game
     */
    private GameController gc;

    /**
     * Initialization of this class for binding containers to resize with window
     */
    public void initialize() {
        resizePanels();
    }

    /*
     * Methods for opening a bowling bar
     */

    /**
     * Gets clicked bar, initializes it and display it
     * @param mouseEvent mouse click
     */
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

        openBowlingBar();
    }

    /**
     * Method to get and show list of games in lanes
     */
    private void openBowlingBar() {
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
                            btn.setText((String) item);
                            btn.setStyle("-fx-text-fill: " + Color.RED);
                            btn.setOnAction(event -> {
                                if (!backButton.isDisable()) {
                                    mainTable.setVisible(false);
                                    topGamesButton.setDisable(true);
                                    leaderboardsButton.setDisable(true);
                                    backButton.setDisable(false);
                                    gameVBox.setVisible(true);

                                    currentGame = gameRepo.findById(getTableView().getItems().get(getIndex()).getRunningGame().getId()).get();
                                    mainLabel.setText(currentGame.getName());
                                    gc = new GameController(getController());
                                    gc.loadGame();
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> newGame(getTableView().getItems().get(getIndex())));
                            btn.setStyle("-fx-text-fill: " + Color.GREEN);
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
        lineID.setStyle("-fx-alignment: CENTER");

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

    /*
     *  Buttons click methods
     */

    /**
     * Method to get and show the list of games in table
     * @param actionEvent
     */
    public void getListOfGames(ActionEvent actionEvent) {
        changeWindowItems(true);

        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<GameEntity, String> game_title = new TableColumn<>("Game title");
        game_title.setMinWidth(100);
        game_title.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<GameEntity, Integer> best_score = new TableColumn<>("Achieved score");
        best_score.setMinWidth(50);
        best_score.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getTotalScore()).asObject());

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

                    /**
                     * After clicking button, show new window with player stats during chosen game
                     * @param game chosen game
                     */
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

        ObservableList<GameEntity> data = FXCollections.observableList(gameRepo.findAllByIdGreaterThanEqualOrderByStartedDesc(0));
        mainTable.setItems(data);
    }

    /**
     * Method to get and show list of players and their statistics in table
     * @param actionEvent
     */
    public void getListOfLeaderboard(ActionEvent actionEvent) {
        changeWindowItems(true);

        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PlayerEntity, String> player_name = new TableColumn<>("Player name");
        player_name.setMinWidth(100);
        player_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PlayerEntity, String> best_score = new TableColumn<>("Best score");
        best_score.setMinWidth(100);
        best_score.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().sumScore() + ""));

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(player_name, best_score);

        ObservableList<PlayerEntity> data = FXCollections.observableList(playerRepo.findByIdGreaterThanEqualOrderByNameDesc(0));
        mainTable.setItems(data);
    }

    /*
     * Methods to for displaying correct containers
     */

    /**
     * Calls new game controller to open windows to create new game
     * @param line new game line
     */
    public void newGame(LineEntity line) {
        if (!backButton.isDisable()) {
            backButton.setDisable(true);
            topGamesButton.setDisable(true);
            leaderboardsButton.setDisable(true);
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
    }

    /**
     * Moving through main window panels (disabling buttons
     * @param next
     */
    private void changeWindowItems(boolean next) {
        if (next) {
            bowlingGridPane.setVisible(false);
            mainTable.setVisible(true);
            backButton.setDisable(false);
        } else {
            if (currentGame == null) {
                currentBowling = null;

                mainTable.setVisible(false);
                bowlingGridPane.setVisible(true);

                backButton.setDisable(true);

                mainLabel.setText("Bowling bars");
            } else {
                gc.endGame();
                gc = null;
                currentGame = null;

                bowlingGridPane.setVisible(false);
                gameVBox.setVisible(false);
                mainTable.setVisible(true);

                leaderboardsButton.setDisable(false);
                topGamesButton.setDisable(false);

                mainLabel.setText(currentBowling.getName());
                clock.stop();
                gameTime.setText("");
                openBowlingBar();
            }
        }
    }

    /**
     * Displaying new running game GUI
     */
    public void newGameGui() {
        mainTable.setVisible(false);
        topGamesButton.setDisable(true);
        leaderboardsButton.setDisable(true);
        backButton.setDisable(false);
        gameVBox.setVisible(true);

        gc = new GameController(this);
        gc.startGame();
    }

    /**
     * Method for binding containers to window size for resizing
     */
    private void resizePanels() {
        mainPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefWidth(mainPane.getLayoutBounds().getWidth());
            bowlingGridPane.setPrefWidth(mainPane.getLayoutBounds().getWidth());
            gameVBox.setPrefWidth(mainPane.getLayoutBounds().getWidth());
        });

        mainPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainTable.setPrefHeight(mainPane.getLayoutBounds().getHeight());
            bowlingGridPane.setPrefHeight(mainPane.getLayoutBounds().getHeight());
            gameVBox.setPrefHeight(mainPane.getLayoutBounds().getHeight());
        });


    }

    /**
     * Defining method when moving back in main window
     * @param actionEvent
     */
    public void goBack(ActionEvent actionEvent) {
        changeWindowItems(false);
    }

    /*
     * Getters and setters
     */

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

    public FrameRepository getFrameRepo() {
        return frameRepo;
    }

    public void setCurrentGame(GameEntity currentGame) {
        this.currentGame = currentGame;
    }

    public GameEntity getCurrentGame() {
        return currentGame;
    }

    private GuiController getController() {
        return this;
    }
}
