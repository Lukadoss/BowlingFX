//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.GameEntity;
//import es.ulpgc.bowling.entity.PlayerEntity;
//import es.ulpgc.bowling.repository.GameRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class GameServiceImpl extends BaseServiceImpl<GameEntity> implements GameService{
//
//    @Autowired
//    GameRepository repository;
//
//    @Autowired
//    PlayerService playerService;
//
//    @Override
//    public CrudRepository<GameEntity, Long> getRepository() {
//        return repository;
//    }
//
//    @Override
//    public void specificValidator(GameEntity entity, Errors errors) {
//
//    }
//
//    @Override
//    public GameEntity addPlayer(GameEntity g, PlayerEntity p) {
//
//        if (isRunning(g)) {
//            if (!g.getPlayers().contains(p)) {
//                g.getPlayers().add(p);
//
//            } else {
////                logger.warn("PlayerEntity " + player.toString() + " is already in game " + this.toString());
//            }
//        } else {
////            logger.warn("GameEntity " + this.toString() + "already finished");
//        }
//        return g;
//    }
//
//    @Override
//    public GameEntity removePlayer(GameEntity g, PlayerEntity p) {
//        if (isRunning(g)) {
//            if (g.getPlayers().contains(p)) {
//                g.getPlayers().remove(p);
////                logger.debug("Removing " + player.toString() + " from game " + this.toString());
//            } else {
////                logger.warn("PlayerEntity " + player.toString() + " is not in game " + this.toString());
//            }
//        } else {
////            logger.warn("GameEntity " + this.toString() + "already finished");
//        }
//        return g;
//    }
//
//    @Override
//    public GameEntity startGame(GameEntity g) {
//        if (g == null) {
//            g = new GameEntity();
//            g.setStarted(LocalDateTime.now());
//            g.setEnded(null);
//        }
//        repository.save(g);
//        return g;
//    }
//
//    @Override
//    public GameEntity endGame(GameEntity g) {
//        g.setEnded(LocalDateTime.now());
//        repository.save(g);
//        return g;
//    }
//
//    @Override
//    public Boolean isRunning(GameEntity g) {
//        return g.getEnded() == null;
//    }
//
//    public PlayerService getPlayerService() {
//        return this.playerService;
//    }
//}
