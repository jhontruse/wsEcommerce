package com.clothing.ecommerce.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder // Builder pattern opcional
public class Usuario {

    private UUID idUsuario;

    private String usuario;

    private String password;

    private String email;

    private Boolean activo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp fecCreacion;

}
