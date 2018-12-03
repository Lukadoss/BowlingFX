//package es.ulpgc.bowling.controllers;
//
//import es.ulpgc.bowling.entity.LineEntity;
//import es.ulpgc.bowling.service.LineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//
//@Controller
//@RequestMapping("/line")
//public class LineController extends BaseController<LineEntity, LineService> {
//
//    @Autowired
//    LineService service;
//
//    @RequestMapping("/{id}")
//    public ModelAndView welcome(Map<String, Object> model, @PathVariable Long id) {
//        LineEntity line = service.get(id);
//        model.put("line", line);
//        return new ModelAndView("line", model);
//    }
//
//    public LineService getService() {
//        return service;
//    }
//}
