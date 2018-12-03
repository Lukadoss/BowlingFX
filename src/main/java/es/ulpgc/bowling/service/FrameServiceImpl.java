//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.FrameEntity;
//import es.ulpgc.bowling.repository.FrameRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//@Service
//public class FrameServiceImpl extends BaseServiceImpl<FrameEntity> implements FrameService {
//
//    @Autowired
//    FrameRepository repository;
//
//    @Autowired
//    PlayerService playerService;
//
//    @Override
//    public CrudRepository<FrameEntity, Long> getRepository() {
//        return repository;
//    }
//
//    @Override
//    public void specificValidator(FrameEntity entity, Errors errors) {
//
//    }
//
//    @Override
//    public Integer getFrameScore(FrameEntity f) {
//        if (f == null) return null;
//        if (!isTerminated(f)) return null;
//        if (isLastFrame(f) && (isStrike(f) || isSpare(f))) return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
//        if (isSpare(f)) return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
//        if (isStrike(f)) {
//            if (playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1) == null) return null;
//            if (isStrike(playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1)) &&
//                    (playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 2)) == null &&
//                    !isLastFrame(playerService.getFrame(f.getPlayer(), f.getFrameIndex() + 1))) return null;
//            return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) + roll(f, f.getRollIndex() + 2);
//        }
//        return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1);
//    }
//
//    public Boolean isLastFrame(FrameEntity f) {
//        return f.getFrameIndex() == 9;
//    }
//
//    private Boolean isTerminated(FrameEntity f) {
//        return f.getRollIndex() != playerService.getRolls(f.getPlayer()).size() - rollsToTerminate(f);
//    }
//
//    private Integer rollsToTerminate(FrameEntity f) {
//        return isSpare(f) || isStrike(f) ? 2 : 1;
//    }
//
//    private Boolean isSpare(FrameEntity f) {
//        if (f.getRollIndex() + 1 >= playerService.getRolls(f.getPlayer()).size()) return false;
//        return roll(f, f.getRollIndex()) + roll(f, f.getRollIndex() + 1) == 10;
//    }
//
//    public Boolean isStrike(FrameEntity f) {
//        return roll(f, f.getRollIndex()) == 10;
//    }
//
//    private Integer roll(FrameEntity f, int rollIndex) {
//        return playerService.getRolls(f.getPlayer()).get(rollIndex);
//    }
//
//    public PlayerService getPlayerService() {
//        return playerService;
//    }
//}
