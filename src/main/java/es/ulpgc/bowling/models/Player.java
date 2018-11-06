package es.ulpgc.bowling.models;

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
        int frameCnt = 0;
        for (int rollCnt = 0; rollCnt < rolls.size();) {
            Frame frame = new Frame(this, rollCnt, frameCnt);
            frames.add(frame);
            rollCnt += frame.isStrike() ? 1 : 2;
            frameCnt++;
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
        try {
            return frames().get(i);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Integer sumScore(int frame) {
        Integer sum = 0;
        for (int i = 0; i <= frame; i++) {
            if (frame(i).score() == null) { return null; }
            sum += frame(i).score();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for (int i = 0; i < rolls.size(); i++) {
            sb.append(" " + rolls.get(i));
        }
        return sb.toString();
    }
}
