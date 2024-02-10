package edu.cibertec.capitulo3.lab01.repository;

import edu.cibertec.capitulo3.lab01.model.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
}
