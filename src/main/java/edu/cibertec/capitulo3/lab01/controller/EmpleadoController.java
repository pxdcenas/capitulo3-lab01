package edu.cibertec.capitulo3.lab01.controller;

import edu.cibertec.capitulo3.lab01.model.Empleado;
import edu.cibertec.capitulo3.lab01.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listar")
    public String listarProductos(){
        List<Empleado> lista = empleadoService.listarTodos();
        return "productos/listar";
    }
}
