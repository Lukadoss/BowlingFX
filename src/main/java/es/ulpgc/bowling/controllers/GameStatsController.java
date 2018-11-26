package es.ulpgc.bowling.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStatsController {
    public AnchorPane anchorPane;
    public Label gameNameLabel;
    public ListView graphicList;

    public void cancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void initGame(int id){
        ResultSet rs = GuiController.sqlExec("SELECT title FROM game WHERE id = "+id);
        try {
            gameNameLabel.setText(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs = GuiController.sqlExec("SELECT name, total_score FROM player LEFT JOIN game WHERE game.id = game_id AND game.id = "+id);
        try {
            ObservableList list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(rs.getString(1)+" : "+rs.getString(2));
            }
            graphicList.setItems(list);
            graphicList.setPrefHeight(24*list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        anchorPane.getScene().getWindow().setHeight(anchorPane.getHeight()+graphicList.getPrefHeight());
    }

}
