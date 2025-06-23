package com.clothing.ecommerce.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.clothing.ecommerce.model.entity.Persona;

public class PersonaRowMapper implements RowMapper<Persona> {

    @Override
    @Nullable
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persona persona = new Persona();
        persona.setDni(rs.getString("DNI"));
        persona.setNombre(rs.getString("NOMBRE"));
        persona.setApePaterno(rs.getString("APE_PATERNO"));
        persona.setApeMaterno(rs.getString("APE_MATERNO"));
        persona.setSexo(rs.getString("SEXO"));
        persona.setTelefono(rs.getString("TELEFONO"));
        persona.setDireccion(rs.getString("DIRECCION"));
        persona.setActivo(rs.getBoolean("ACTIVO"));
        return persona;
    }

}
