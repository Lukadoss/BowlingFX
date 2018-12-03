//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.*;
//import es.ulpgc.bowling.repository.PlayerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PlayerServiceImpl extends BaseServiceImpl<PlayerEntity> implements PlayerService {
//
//    @Autowired
//    PlayerRepository repository;
//
//    @Autowired
//    FrameService frameService;
//
//    @Override
//    public CrudRepository<PlayerEntity, Long> getRepository() {
//        return repository;
//    }
//
//    @Override
//    public void specificValidator(PlayerEntity entity, Errors errors) {
//
//    }
//
//    @Override
//    public List<PlayerEntity> findPlayersByGame(GameEntity game) {
//        return null;
//    }
//
//    @Override
//    public List<FrameEntity> getFrames(PlayerEntity p) {
//        ArrayList<FrameEntity> frames = new ArrayList<>();
//        int frameIndex = 0;
//        for (int rollIndex = 0; rollIndex < p.getRolls().size();) {
//            FrameEntity frame = new FrameEntity();
//            frame.setFrameIndex(frameIndex);
//            frame.setRollIndex(rollIndex);
//            frame.setPlayer(p);
//            frames.add(frame);
//            rollIndex += frameService.isLastFrame(frame) ? 3 : (frameService.isStrike(frame)) ? 1 : 2;
//            frameIndex++;
//        }
//        return frames;
//    }
//    @Override
//    public FrameEntity getFrame(PlayerEntity p, int i) {
//        if (p.getFrames().size() <= i) return null;
//        return p.getFrames().get(i);
//    }
//
//    @Override
//    public List<Integer> getRolls(PlayerEntity p) {
//        return p.getRolls();
//    }
//
//    @Override
//    public void addRoll(PlayerEntity p, int pins) {
//        p.getRolls().add(pins);
//        repository.save(p);
//    }
//
//    public FrameService getFrameService () {
//        return this.frameService;
//    }
//}
