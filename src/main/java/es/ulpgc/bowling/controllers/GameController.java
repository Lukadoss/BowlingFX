package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService service;

    @RequestMapping("/{id}")
    public ModelAndView welcome(Map<String, Object> model, @PathVariable Long id) {
        Game game = service.get(id);
        model.put("game", game);
        return new ModelAndView("game", model);
    }

    public GameService getService() {
        return service;
    }
}
