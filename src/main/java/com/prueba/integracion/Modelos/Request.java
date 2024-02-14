package com.prueba.integracion.Modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Request {
    @JsonProperty("Nombre")
    private String nombre;
    @JsonProperty("Apellido")
    private String apellido;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
