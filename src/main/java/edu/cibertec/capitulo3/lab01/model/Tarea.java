package edu.cibertec.capitulo3.lab01.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_tareas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    @ToString.Exclude
    private Empleado empleado;

}
