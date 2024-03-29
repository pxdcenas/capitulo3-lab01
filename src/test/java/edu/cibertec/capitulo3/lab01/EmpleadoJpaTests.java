package edu.cibertec.capitulo3.lab01;

import edu.cibertec.capitulo3.lab01.model.*;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Commit
//@Rollback(false)
class EmpleadoJpaTests {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Commit
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


        Empleado empleadoGuardado = entityManager.persist(nuevoEmpleado);

        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isNotNull();
        assertThat(empleadoGuardado.getId()).isPositive();
        assertThat(empleadoGuardado.getApellidoPaterno()).isEqualTo("Cenas");
        assertThat(empleadoGuardado.getNroHijos()).isEqualTo(5);
        assertTrue(empleadoGuardado.getNroHijos() > 0);
        assertThat(empleadoGuardado.getSueldo()).isEqualByComparingTo(new BigDecimal("1025.00"));
        assertThat(empleadoGuardado.getNombres()).isNotBlank();
        assertThat(empleadoGuardado.getNombres().length()).isLessThanOrEqualTo(100);

    }

    @Test
    void testListarEmpleadosQuerysNombradas(){
//        TypedQuery<Empleado> lista = entityManager.getEntityManager().createQuery("SELECT e FROM Empleado e", Empleado.class);
        TypedQuery<Empleado> lista = entityManager.getEntityManager().createNamedQuery("Empleado.listarTodos3", Empleado.class);
        assertThat(lista.getResultList()).isNotEmpty();

        List<Empleado> empleados = lista.getResultList();

        for(Empleado e: empleados){
            System.out.println("e = " + e);
        }
    }

    @Test
    void testListarEmpleadosQuerysNativas(){
        //List<Empleado> empleados = entityManager.getEntityManager().createNativeQuery("SELECT * FROM tbl_empleados", Empleado.class).getResultList();
        List<Empleado> empleados = entityManager.getEntityManager().createNamedQuery("Empleado.listar", Empleado.class).getResultList();
        assertThat(empleados).isNotEmpty();

        for(Empleado e: empleados){
            System.out.println("e = " + e);
        }
    }

    @Test
    void testBuscarEmpleadoPorId(){
        Empleado empleadoEncontrado = entityManager.find(Empleado.class, 4);
        assertThat(empleadoEncontrado).isNotNull();
        assertThat(empleadoEncontrado.getNombres()).isEqualTo("Dany");
    }

    @Test
    void testActualizarEmpleado(){
        Empleado empleado = entityManager.find(Empleado.class, 4);
        empleado.setNombres("Juan");
        Empleado empleadoActualizado = entityManager.merge(empleado);
        assertThat(empleadoActualizado).isNotNull();
        assertThat(empleadoActualizado.getNombres()).isEqualTo("Juan");
    }

    @Test
    @Commit
    void testEliminarEmpleadoPorId(){
        Empleado empleadoEncontrado = entityManager.find(Empleado.class, 6);
        entityManager.remove(empleadoEncontrado);
        Empleado empleadoEliminado = entityManager.find(Empleado.class, 6);
        assertThat(empleadoEliminado).isNull();
    }

    @Test
    void testBuscarEmpleadoPorSueldoParametroPosicion(){
        BigDecimal sueldo = new BigDecimal("5000.00");
        List<Empleado> empleados = entityManager.getEntityManager().createNamedQuery("Empleado.buscarPorSueldo", Empleado.class)
                .setParameter(1, sueldo)
                .getResultList();
        assertThat(empleados).isNotEmpty();
        assertThat(empleados).hasSize(3);
    }

    @Test
    void testBuscarEmpleadoPorSueldoParametroSueldo(){
        BigDecimal sueldo = new BigDecimal("5000.00");
        List<Empleado> empleados = entityManager.getEntityManager().createNamedQuery("Empleado.buscarPorSueldo2", Empleado.class)
                .setParameter("sueldo", sueldo)
                .getResultList();
        assertThat(empleados).isNotEmpty();
        assertThat(empleados).hasSize(3);
    }

    @Test
    void testBuscarEmpleadoPorFechaDeNacimiento(){
        LocalDate fechaInicio = LocalDate.of(1990,1,1);
        LocalDate fechaFin = LocalDate.of(1995,12,31);
        List<Empleado> empleados = entityManager.getEntityManager().createNamedQuery("Empleado.buscarPorFechaNacimiento", Empleado.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
        assertThat(empleados).isNotEmpty();
        assertThat(empleados).hasSize(3);
    }

    @Test
    void testBuscarEmpleadoPorIdStoreProcedure(){
        StoredProcedureQuery query = entityManager.getEntityManager().createNamedStoredProcedureQuery("Empleado.ObtenerEmpleadoPorId");
        query.setParameter("_id", 8L);

        Empleado empleado = (Empleado) query.getSingleResult();

        assertThat(empleado).isNotNull();
        assertThat(empleado.getNombres()).isEqualTo("Sofia");
    }

    @Test
    void testBuscarEmpleadoPorSueldoQuery(){
        BigDecimal sueldo = new BigDecimal("4000.00");
        Query query = entityManager.getEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.sueldo > :sueldo", Empleado.class);
        query.setParameter("sueldo", sueldo);
        List<Empleado> lista = (List<Empleado>) query.getResultList();
        assertThat(lista).isNotEmpty();
        assertThat(lista).hasSize(5);
    }

    @Test
    void testEmpleadosFetch() throws IOException {
        Empleado empleado = entityManager.find(Empleado.class, 1L);
        System.out.println("empleado = " + empleado);
        assertThat(empleado).isNotNull();
        assertThat(empleado.getDepartamento()).isNotNull();
        assertThat(empleado.getEstacionamiento()).isNotNull();
        System.out.println("tareas = " + empleado.getTareas());
        System.out.println("proyectos = " + empleado.getProyectos());
    }
}
