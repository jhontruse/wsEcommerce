package com.clothing.ecommerce.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.clothing.ecommerce.model.entity.Rol;

public class RolRowMapper implements RowMapper<Rol> {

    @Override
    @Nullable
    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rol rol = new Rol();
        rol.setIdRol(java.util.UUID.fromString(rs.getString("ID_ROL")));
        rol.setNombre(rs.getString("NOMBRE"));
        rol.setDetalle(rs.getString("DETALLE"));
        rol.setFecCreacion(rs.getTimestamp("FEC_CREACION"));
        rol.setActivo(rs.getBoolean("ACTIVO"));
        return rol;
    }

}
