package com.clothing.ecommerce.model.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder // Builder pattern opcional
public class UsuarioRol {

    private UUID idUsuario;

    private UUID idRol;

}
