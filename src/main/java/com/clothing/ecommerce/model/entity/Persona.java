package com.clothing.ecommerce.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Constructor vacío
@AllArgsConstructor     // Constructor con todos los campos
@Builder                // Builder pattern opcional
public class Persona {

    private String dni;

    private String nombre;

    private String apePaterno;

    private String apeMaterno;

    private String sexo;

    private String telefono;

    private String direccion;

    private Boolean activo;

}
