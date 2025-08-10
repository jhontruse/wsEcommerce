package com.clothing.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.clothing.ecommerce.model.dto.UsuarioDTO;
import com.clothing.ecommerce.model.entity.Usuario;

public interface IUsuarioService {

    List<Map<String, Object>> findAllUsuarioDTO(int page, int size);

    void insertUsuarioByUsuario(UsuarioDTO usuarioDTO);

    Usuario updateUsuarioByUsuario(UsuarioDTO usuarioDTO);

    void deleteUsuarioByUsuario(UUID idUsuario);

    Usuario findUsuarioById(UUID idUsuario);

    Map<String, Object> findAllUsuariosByPersonaId(UUID idPersona);

    Usuario findUsuariosByUsuario(String usuario);

}
