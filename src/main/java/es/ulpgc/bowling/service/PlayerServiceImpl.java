package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.*;
import es.ulpgc.bowling.repository.PlayerRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PlayerServiceImpl extends BaseServiceImpl<Player> implements PlayerService {

    @Autowired
    PlayerRepository repository;

    @Autowired
    FrameService frameService;

    @Override
    public CrudRepository<Player, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Player entity, Errors errors) {

    }

    @Override
    public List<Player> findPlayersByGame(Game game) {
        return null;
    }

    @Override
    public Integer score(Player p) {
        Integer sum = 0;
        for (int i = 0; i <= p.getFrames().size(); i++) {
            sum += frameService.getFrameScore(getFrame(p, i));
        }
        return sum;
    }

    @Override
    public Integer scoreUntilFrame(Player p, int frame) {
        Integer sum = 0;
        for (int i = 0; i <= frame; i++) {
            if (frameService.getFrameScore(getFrame(p, i)) == null) { return null; }
            sum += frameService.getFrameScore(getFrame(p, i));
        }
        return sum;
    }

    @Override
    public List<Frame> getFrames(Player p) {
        ArrayList<Frame> frames = new ArrayList<>();
        int frameIndex = 0;
        for (int rollIndex = 0; rollIndex < p.getRolls().size();) {
            Frame frame = new Frame();
            frame.setFrameIndex(frameIndex);
            frame.setRollIndex(rollIndex);
            frame.setPlayer(p);
            frames.add(frame);
            rollIndex += frameService.isLastFrame(frame) ? 3 : (frameService.isStrike(frame)) ? 1 : 2;
            frameIndex++;
        }
        return frames;
    }
    @Override
    public Frame getFrame(Player p, int i) {
        if (p.getFrames().size() <= i) return null;
        return p.getFrames().get(i);
    }

    @Override
    public List<Integer> getRolls(Player p) {
        return p.getRolls();
    }

    public FrameService getFrameService () {
        return this.frameService;
    }
}
