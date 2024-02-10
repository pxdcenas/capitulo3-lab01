package edu.cibertec.capitulo3.lab01;


import edu.cibertec.capitulo3.lab01.model.*;
import edu.cibertec.capitulo3.lab01.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void testGuardarEmpleado() throws IOException {

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setApellidoPaterno("Cenas");
        nuevoEmpleado.setApellidoMaterno("Vásquez");
        nuevoEmpleado.setNombres("Dany");
        nuevoEmpleado.setNroHijos(5);
        nuevoEmpleado.setSueldo(new BigDecimal("1025.00"));
        nuevoEmpleado.setFechaNacimiento(LocalDate.of(1995, 5, 9));

        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/images/goku.jpg"));
        nuevoEmpleado.setFoto(bytes);

        Departamento departamento = new Departamento();
        departamento.setDescripcion("IT");
        nuevoEmpleado.setDepartamento(departamento);

        Estacionamiento estacionamiento = new Estacionamiento();
        estacionamiento.setUbicacion("A1");
        nuevoEmpleado.setEstacionamiento(estacionamiento);

        Tarea tarea1 = new Tarea();
        tarea1.setDescripcion("Tarea 1");
        Tarea tarea2 = new Tarea();
        tarea2.setDescripcion("Tarea 2");
        nuevoEmpleado.setTareas(Arrays.asList(tarea1, tarea2));

        Proyecto proyecto1 = new Proyecto();
        proyecto1.setDescripcion("Proyecto 1");
        Proyecto proyecto2 = new Proyecto();
        proyecto2.setDescripcion("Proyecto 2");
        Proyecto proyecto3 = new Proyecto();
        proyecto3.setDescripcion("Proyecto 3");
        nuevoEmpleado.setProyectos(Arrays.asList(proyecto1, proyecto2, proyecto3));


        Empleado empleadoGuardado = empleadoRepository.save(nuevoEmpleado);
        assertThat(empleadoGuardado).isNotNull();
    }

    @Test
    void testActualizarEmpleado() throws IOException {

        Empleado empleadoEncontrado = empleadoRepository.findById(5L).get();
        empleadoEncontrado.setSueldo(new BigDecimal("1000.00"));
        empleadoEncontrado.setFechaNacimiento(LocalDate.of(2020, 1, 1));

        Empleado empleadoGuardado = empleadoRepository.save(empleadoEncontrado);
        assertThat(empleadoGuardado).isNotNull();
    }

    @Test
    void testEliminarEmpleado() throws IOException {
        empleadoRepository.deleteById(2L);
        Optional<Empleado> empleadoEliminado= empleadoRepository.findById(2L);
        assertFalse(empleadoEliminado.isPresent());
    }

}
