package com.clothing.ecommerce.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.clothing.ecommerce.model.entity.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    private static final String COL_ID_USUARIO = "ID_USUARIO";
    private static final String COL_USUARIO = "USUARIO";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_ACTIVO = "ACTIVO";
    private static final String COL_FEC_CREACION = "FEC_CREACION";

    @Override
    @Nullable
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(UUID.fromString(rs.getString(COL_ID_USUARIO)));
        usuario.setUsuario(rs.getString(COL_USUARIO));
        usuario.setPassword(rs.getString(COL_PASSWORD));
        usuario.setEmail(rs.getString(COL_EMAIL));
        usuario.setActivo(rs.getBoolean(COL_ACTIVO));
        usuario.setFecCreacion(rs.getTimestamp(COL_FEC_CREACION));
        return usuario;

    }

}
