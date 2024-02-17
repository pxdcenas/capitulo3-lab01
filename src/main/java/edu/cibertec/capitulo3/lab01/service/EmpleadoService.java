package edu.cibertec.capitulo3.lab01.service;

import edu.cibertec.capitulo3.lab01.model.Empleado;

import java.util.List;

public interface EmpleadoService {

    List<Empleado> listarTodos();

    Empleado registrarEmpleado(Empleado empleado);

    Empleado actualizarEmpleado(Empleado empleado);

    Empleado buscarEmpleado(Long id);

    void eliminarEmpleado(Long id);
}
