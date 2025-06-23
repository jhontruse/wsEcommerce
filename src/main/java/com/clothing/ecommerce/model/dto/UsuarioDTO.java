package com.clothing.ecommerce.model.dto;

import java.util.List;
import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.model.entity.Rol;
import com.clothing.ecommerce.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder // Builder pattern opcional
public class UsuarioDTO {

    private Usuario usuario;

    private Persona persona;

    private List<Rol> rol;

}
