package edu.cibertec.capitulo3.lab01.repository;

import edu.cibertec.capitulo3.lab01.model.Departamento;
import edu.cibertec.capitulo3.lab01.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Empleado findByNombres(String nombres);

    @Query(name = "Empleado.listarTodos")
    List<Empleado> listarEmpleados();

    @Query("SELECT e FROM Empleado e WHERE e.apellidoPaterno = :apellido OR e.apellidoMaterno = :apellido")
    List<Empleado> findByApellidoPaternoOrApellidoMaterno(@Param("apellido") String ape);

    List<Empleado> findByFechaNacimientoBefore(LocalDate xyz);

    List<Empleado> findBySueldoBetween(BigDecimal sueldoMin, BigDecimal sueldoMax);

    List<Empleado> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Empleado> findByNombresLike(String abc);

    List<Empleado> findByDepartamentoOrderByApellidoPaterno(Departamento departamento);

}
