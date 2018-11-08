package es.ulpgc.bowling.service;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl {
    private List<Integer> rolls;
    private String name;

    public PlayerServiceImpl(String name) {
        this.name = name;
        this.rolls = new ArrayList<>();
    }

    public List<FrameServiceImpl> frames() {
        ArrayList<FrameServiceImpl> frames = new ArrayList<>();
        int frameCnt = 0;
        for (int rollCnt = 0; rollCnt < rolls.size();) {
            FrameServiceImpl frame = new FrameServiceImpl(this, rollCnt, frameCnt);
            frames.add(frame);
            rollCnt += frame.isLastFrame() ? 3 : (frame.isStrike()) ? 1 : 2;
            frameCnt++;
        }
        return frames;
    }

    public PlayerServiceImpl roll(int pins) {
        rolls.add(pins);
        return this;
    }

    public List<Integer> getRolls(){
        return this.rolls;
    }

    public FrameServiceImpl frame(int i) {
        if (frames().size() <= i) return null;
        return frames().get(i);
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
