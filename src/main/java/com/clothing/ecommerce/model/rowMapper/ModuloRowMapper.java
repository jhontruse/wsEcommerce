package com.clothing.ecommerce.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.clothing.ecommerce.model.entity.Modulo;

public class ModuloRowMapper implements RowMapper<Modulo> {

    private static final String COL_ID_MODULO = "ID_MODULO";
    private static final String COL_NOMBRE = "NOMBRE";
    private static final String COL_URL = "URL";
    private static final String COL_ICONO = "ICONO";
    private static final String COL_DETALLE = "DETALLE";
    private static final String COL_ACTIVO = "ACTIVO";
    private static final String COL_FEC_CREACION = "FEC_CREACION";

    @Override
    @Nullable
    public Modulo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(UUID.fromString(rs.getString(COL_ID_MODULO)));
        modulo.setNombre(rs.getString(COL_NOMBRE));
        modulo.setUrl(rs.getString(COL_URL));
        modulo.setIcono(rs.getString(COL_ICONO));
        modulo.setDetalle(rs.getString(COL_DETALLE));
        modulo.setActivo(rs.getBoolean(COL_ACTIVO));
        modulo.setFecCreacion(rs.getTimestamp(COL_FEC_CREACION));
        return modulo;
    }

}
