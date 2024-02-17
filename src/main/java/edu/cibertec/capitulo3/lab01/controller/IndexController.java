package edu.cibertec.capitulo3.lab01.controller;

import edu.cibertec.capitulo3.lab01.model.Empleado;
import edu.cibertec.capitulo3.lab01.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/abc")
    public String index(){
        logger.info("Path: /abc");
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e){
            logger.error("Exception: " + e.getMessage());
        }
        return "index";
    }

}
