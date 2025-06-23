package com.clothing.ecommerce.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder // Builder pattern opcional
public class Modulo {

    private UUID idModulo;

    private String nombre;

    private String url;

    private String icono;

    private String detalle;

    private Boolean activo;

    private Timestamp fecCreacion;

}
