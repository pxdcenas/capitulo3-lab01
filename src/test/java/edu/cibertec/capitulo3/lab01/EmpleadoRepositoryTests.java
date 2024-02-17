package edu.cibertec.capitulo3.lab01;


import edu.cibertec.capitulo3.lab01.model.*;
import edu.cibertec.capitulo3.lab01.repository.DepartamentoRepository;
import edu.cibertec.capitulo3.lab01.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository deparamentoRepository;

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
//        nuevoEmpleado.setTareas(Arrays.asList(tarea1, tarea2));

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

    @Test
    void testBuscarPorNombres() throws IOException {
        Empleado empleadoEncontrado = empleadoRepository.findByNombres("Maria");
        assertThat(empleadoEncontrado.getNombres()).isEqualTo("Maria");
    }

    @Test
    void testListarEmpleados() throws IOException {
        List<Empleado> lista = empleadoRepository.listarEmpleados();
        for (int i = 0; i <lista.size(); i++) {
            System.out.println("lista = " + lista.get(i));
        }
        assertThat(lista).isNotEmpty();
        assertThat(lista).hasSize(10);
    }

    @Test
    void testEmpleadosFetch() throws IOException {
        Empleado empleado = empleadoRepository.findById(1L).orElse(null);
        System.out.println("empleado = " + empleado);
        assertThat(empleado).isNotNull();
        // EAGER: Carga todos los datos relacionados
        assertThat(empleado.getDepartamento()).isNotNull();
        assertThat(empleado.getEstacionamiento()).isNotNull();
        // LAZY: No carga los datos relacionados
        System.out.println("tareas = " + empleado.getTareas());
        System.out.println("proyectos = " + empleado.getProyectos());
        assertThat(empleado.getTareas()).isNotEmpty();
        assertThat(empleado.getProyectos()).isNotEmpty();
    }

    @Test
    @Transactional
    void testEmpleadosFetch2() throws IOException {
        Empleado empleado = empleadoRepository.findById(1L).orElse(null);
        // LAZY: @Transactional mantiene la sesión de hibernate
        System.out.println("tareas = " + empleado.getTareas().size());
        System.out.println("proyectos = " + empleado.getProyectos().size());
        assertThat(empleado.getTareas()).isNotEmpty();
        assertThat(empleado.getProyectos()).isNotEmpty();
    }

    @Test
    void testEmpleadosFetchTareasYProyectos() throws IOException {
        Empleado empleado = empleadoRepository.obtenerTareasYProyectos(1L);
        // LAZY: empleadoRepository en obtenerTareasYProyectos() tiene @Transactional
        System.out.println("tareas = " + empleado.getTareas().size());
        System.out.println("proyectos = " + empleado.getProyectos().size());
        assertThat(empleado.getTareas()).isNotEmpty();
        assertThat(empleado.getProyectos()).isNotEmpty();
    }


    @Test
    void testBuscarPorApellidoPaternoOrApellidoMaterno() throws IOException {
        List<Empleado> lista = empleadoRepository.findByApellidoPaternoOrApellidoMaterno("Perez");
        assertThat(lista).isNotEmpty();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("lista.get(i) = " + lista.get(i));
        }
        assertThat(lista).hasSize(2);
    }

    @Test
    void testListarEmpleadosFechaDeNacimientoAntesDe() throws IOException {
        List<Empleado> lista = empleadoRepository.findByFechaNacimientoBefore(LocalDate.of(1999, 1, 1));
        assertThat(lista).hasSize(8);
    }

    @Test
    void testListarEmpleadosSueldoEntre() throws IOException {
        BigDecimal sueldoMin = new BigDecimal(3500);
        BigDecimal sueldoMax = new BigDecimal(5500);
        List<Empleado> lista = empleadoRepository.findBySueldoBetween(sueldoMin, sueldoMax);
        assertThat(lista).hasSize(5);
    }

    @Test
    void testListarEmpleadosFechaDeNacimientoEntre() throws IOException {
        LocalDate fechaInicio = LocalDate.of(1987,1,1);
        LocalDate fechaFin = LocalDate.of(1999,12,31);
        List<Empleado> lista = empleadoRepository.findByFechaNacimientoBetween(fechaInicio, fechaFin);
        assertThat(lista).hasSize(5);
    }

    @Test
    void testListarEmpleadosQueNombreContiene() throws IOException {
        List<Empleado> lista = empleadoRepository.findByNombresLike("%ar%");
        assertThat(lista).hasSize(3);
    }

    @Test
    void testListarEmpleadosPorDepartamento() throws IOException {
        Departamento departamento = deparamentoRepository.findById(2L).orElse(null);
        List<Empleado> lista = empleadoRepository.findByDepartamentoOrderByApellidoPaterno(departamento);
        assertThat(lista).hasSize(3);
    }


    @Test
    void testPagination() throws IOException {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Empleado> pageEmpleados = empleadoRepository.findAll(pageRequest);
        assertThat(pageEmpleados.getContent()).hasSize(10);
    }

    @Test
    void testPaginationPorApellidoPaterno() throws IOException {
        // Orden ascendente
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("apellidoPaterno"));
        Page<Empleado> pageEmpleados = empleadoRepository.findAll(pageRequest);
        assertThat(pageEmpleados.getContent()).isSortedAccordingTo(Comparator.comparing(Empleado::getApellidoPaterno));
    }

    @Test
    void testPaginationPorApellidoPaterno2() throws IOException {
        // Orden descendente
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "apellidoPaterno"));
        Page<Empleado> pageEmpleados = empleadoRepository.findAll(pageRequest);
        assertEquals(5, pageEmpleados.getSize());
    }


}
