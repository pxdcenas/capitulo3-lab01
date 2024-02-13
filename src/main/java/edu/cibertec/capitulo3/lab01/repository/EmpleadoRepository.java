package edu.cibertec.capitulo3.lab01.repository;

import edu.cibertec.capitulo3.lab01.model.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    Empleado findByNombres(String nombres);

    @Query("SELECT e FROM Empleado e WHERE e.apellidoPaterno = :apellido OR e.apellidoMaterno = :apellido")
    List<Empleado> findByApellidoPaternoOrApellidoMaterno(@Param("apellido") String ape);



}
