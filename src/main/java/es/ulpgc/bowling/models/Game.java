package es.ulpgc.bowling.models;

public class Game {
    private String name;
    private int score;

    public Game(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }
}
