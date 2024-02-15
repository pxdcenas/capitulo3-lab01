package edu.cibertec.capitulo3.lab01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/abc")
    public String index(){
        logger.info("Hello debug en Index");
        return "index";
    }
}
