package com.clothing.ecommerce.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Constructor vacío
@AllArgsConstructor     // Constructor con todos los campos
@Builder                // Builder pattern opcional
public class Rol {

    private UUID idRol;

    private String nombre;

    private String detalle;

    private Timestamp fecCreacion;

    private Boolean activo;

}
