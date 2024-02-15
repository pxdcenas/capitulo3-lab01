package edu.cibertec.capitulo3.lab01.repository;

import edu.cibertec.capitulo3.lab01.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
}
