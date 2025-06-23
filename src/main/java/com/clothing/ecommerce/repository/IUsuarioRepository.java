package com.clothing.ecommerce.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.clothing.ecommerce.model.dto.UsuarioDTO;
import com.clothing.ecommerce.model.entity.Usuario;

public interface IUsuarioRepository {

    Usuario findUsuariosByUsuario(String usuario);

    List<String> getRolesByUsuarioId(UUID idUsuario);

    List<Map<String, Object>> findAllUsuarioDTO(int page, int size);

    void insertUsuarioByUsuario(UsuarioDTO usuarioDTO);

    Usuario updateUsuarioByUsuario(UsuarioDTO usuarioDTO);

    void deleteUsuarioByUsuario(UUID idUsuario);

    Usuario findUsuarioById(UUID idUsuario);

    Map<String, Object> findAllUsuariosByPersonaId(UUID idUsuario);

}
