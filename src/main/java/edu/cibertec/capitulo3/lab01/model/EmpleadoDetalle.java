package edu.cibertec.capitulo3.lab01.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class EmpleadoDetalle {

    private String email;
    private String telefono;
}
