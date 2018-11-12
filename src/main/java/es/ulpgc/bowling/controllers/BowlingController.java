package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.service.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bowling")
public class BowlingController extends BaseController<Bowling, BowlingService> {

    @Autowired
    BowlingService service;

    @RequestMapping("/")
    public ModelAndView bowling(Map<String, Object> model) {
        List<Bowling> list = service.getAll();
        model.put("bowlings",list);
        return new ModelAndView("bowling", model);
    }

    public BowlingService getService() {
        return service;
    }
}
