//package es.ulpgc.bowling.controllers;
//
//import es.ulpgc.bowling.entity.GameEntity;
//import es.ulpgc.bowling.entity.PlayerEntity;
//import es.ulpgc.bowling.service.FrameService;
//import es.ulpgc.bowling.service.GameService;
//import es.ulpgc.bowling.service.PlayerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//
//@Controller
//@RequestMapping("/game")
//public class GameController {
//
//    @Autowired
//    GameService service;
//
//    @Autowired
//    PlayerService playerService;
//
//    @Autowired
//    FrameService frameService;
//
//    @RequestMapping("/{id}")
//    public ModelAndView welcome(Map<String, Object> model, @PathVariable Long id) {
//        GameEntity game = service.get(id);
//        model.put("game", game);
//        model.put("players", service.getPlayers(game));
//        return new ModelAndView("game", model);
//    }
//
//    @RequestMapping(value = "/{gameId}/{playerId}/{rolls}", method = RequestMethod.POST)
//    public ModelAndView roll(@PathVariable Long playerId, @PathVariable Integer rolls) {
//        PlayerEntity p = playerService.get(playerId);
//        playerService.addRoll(p, rolls);
//
//        return new ModelAndView("redirect:/game/{gameId}");
//    }
//
//    public GameService getService() {
//        return service;
//    }
//
//    public PlayerService getPlayerService() { return playerService; }
//
//    public FrameService getFrameService() {return frameService; }
//}
