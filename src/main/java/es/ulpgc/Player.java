package es.ulpgc;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Integer> rolls;
    private String name;

    public Player(String name) {
        this.name = name;
        this.rolls = new ArrayList<>();
    }

    public List<Frame> frames() {
        ArrayList<Frame> frames = new ArrayList<>();
        for (int i = 0; i < rolls.size();) {
            Frame frame = new Frame(this, i);
            frames.add(frame);
            i += frame.isStrike() ? 1 : 2;
        }
        return frames;
    }

    public Player roll(int pins) {
        rolls.add(pins);
        return this;
    }

    public List<Integer> getRolls(){
        return this.rolls;
    }

    public Frame frame(int i) {
        return frames().get(i);
    }
}
