package edu.cibertec.capitulo3.lab01.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_empleados")
@Getter
@Setter
@ToString
//@NamedQuery(name = "Empleado.listarTodos", query = "SELECT e FROM Empleado e")
@NamedQueries({
        @NamedQuery(name = "Empleado.listarTodos", query = "SELECT e FROM Empleado e"),
        @NamedQuery(name = "Empleado.listarTodos2", query = "SELECT e FROM Empleado e"),
        @NamedQuery(name = "Empleado.listarTodos3", query = "SELECT e FROM Empleado e"),
})
//@NamedNativeQuery(name = "Empleado.listar", query = "SELECT * FROM tbl_empleados", resultClass = Empleado.class)
@NamedNativeQueries({
        @NamedNativeQuery(name = "Empleado.listar", query = "SELECT * FROM tbl_empleados", resultClass = Empleado.class)
})
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ape_pat", length = 100, nullable = false)
    private String apellidoPaterno;

    @Column(name = "ape_mat", length = 100, nullable = false)
    private String apellidoMaterno;

    @Column(name = "nombres", length = 50, nullable = false)
    private String nombres;

    private Integer nroHijos;

    @Column(name = "sueldo", precision = 10, scale = 2)
    private BigDecimal sueldo;

    private LocalDate fechaNacimiento;

    @Transient
    private Integer edad;

    @Lob
    @Column(length = 16777215)
    @ToString.Exclude
    private byte[] foto;

    @Embedded
    private EmpleadoDetalle empleadoDetalle;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, unique = true)
    private Estacionamiento estacionamiento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.PERSIST)
    private List<Tarea> tareas;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Proyecto> proyectos;

}
