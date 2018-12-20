package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class GameStatsController {
    public AnchorPane anchorPane;
    public Label gameNameLabel;
    public ListView<String> graphicList;

    public void cancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void initGame(GameEntity game) {
        System.out.println(game);
        List<PlayerEntity> playerEntities = game.getPlayers();
        List<String> statistics = new ArrayList<>();
        for (PlayerEntity p : playerEntities) {
            statistics.add(p.getName() + ":\t" + p.sumScore());
        }
        ObservableList<String> list = FXCollections.observableList(statistics);
        graphicList.setItems(list);
        graphicList.setPrefHeight(24 * list.size());

        anchorPane.getScene().getWindow().setHeight(anchorPane.getHeight() + graphicList.getPrefHeight());
    }
}
