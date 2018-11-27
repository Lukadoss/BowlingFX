package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Frame;
import es.ulpgc.bowling.repository.FrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class FrameServiceImpl extends BaseServiceImpl<Frame> implements FrameService {

    @Autowired
    FrameRepository repository;

    @Autowired
    PlayerService playerService;

    @Override
    public CrudRepository<Frame, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Frame entity, Errors errors) {

    }

    @Override
    public Integer getFrameScore(Frame f) {
        if (!isTerminated(f)) return null;
        if (isLastFrame(f) && (isStrike(f) || isSpare(f))) return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
        if (isSpare(f)) return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
        if (isStrike(f)) {
            if (playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1) == null) return null;
            if (isStrike(playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1)) &&
                    (playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 2)) == null &&
                    !isLastFrame(playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1))) return null;
            return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
        }
        return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1);
    }

    public Boolean isLastFrame(Frame f) {
        return f.getFrameIndex() == 9;
    }

    private Boolean isTerminated(Frame f) {
        return f.getRollIndex() != playerService.getRolls(f.getPlayer()).size() - rollsToTerminate(f);
    }

    private Integer rollsToTerminate(Frame f) {
        return isSpare(f) || isStrike(f) ? 2 : 1;
    }

    private Boolean isSpare(Frame f) {
        if (f.getRollIndex() + 1 >= playerService.getRolls(f.getPlayer()).size()) return false;
        return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) == 10;
    }

    public Boolean isStrike(Frame f) {
        return roll(f, f.getRollIndex()) == 10;
    }

    private Integer roll(Frame f, int rollIndex) {
        return playerService.getRolls(f.getPlayer()).get(rollIndex);
    }

    public PlayerService getPlayerService() {
        return playerService;
    }
}
