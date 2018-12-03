package es.ulpgc.bowling.models;

public class Game {
    private String name;
    private int score;
    private int id = -1;

    public Game(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName(){
        return name;
    }

    public void setScore(int score){ this.score = score;}

    public int getScore(){
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
