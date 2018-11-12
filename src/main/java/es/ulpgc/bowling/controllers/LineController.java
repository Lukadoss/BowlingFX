package es.ulpgc.bowling.controllers;

import es.ulpgc.bowling.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService service;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", service.getAll().get(0).toString());
        return "bowling";
    }

    public LineService getService() {
        return service;
    }
}
