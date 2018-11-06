package es.ulpgc.bowling.models;

import es.ulpgc.bowling.controllers.GuiController;

public class Game {
    String name;
    int players;
    GuiController bc;

    public Game(String name, int players) {
        bc = new GuiController();
        if (!name.isEmpty()) this.name = name;
        else {
            this.name = "Just a game";
        }
        this.players = players;

    }
}
