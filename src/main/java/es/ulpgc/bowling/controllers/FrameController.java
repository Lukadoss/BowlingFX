//package es.ulpgc.bowling.controllers;
//
//import es.ulpgc.bowling.service.FrameService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Map;
//
//@Controller
//@RequestMapping("/frame")
//public class FrameController {
//
//    @Autowired
//    FrameService service;
//
//    @RequestMapping("/")
//    public String welcome(Map<String, Object> model) {
//        model.put("message", service.getAll().get(0).toString());
//        return "bowling";
//    }
//
//    public FrameService getService() {
//        return service;
//    }
//}
