package com.clothing.ecommerce.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.DuplicateKeyException;
import com.clothing.ecommerce.exception.capas.RepositoryException;
import com.clothing.ecommerce.model.dto.UsuarioDTO;
import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.model.entity.Rol;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.model.rowMapper.PersonaRowMapper;
import com.clothing.ecommerce.model.rowMapper.RolRowMapper;
import com.clothing.ecommerce.model.rowMapper.UsuarioRowMapper;
import com.clothing.ecommerce.repository.IUsuarioRepository;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    private final UsuarioRowMapper usuarioMapper = new UsuarioRowMapper();
    private final PersonaRowMapper personaMapper = new PersonaRowMapper();
    private final RolRowMapper rolMapper = new RolRowMapper();

    @Override
    public Usuario findUsuariosByUsuario(String usuario) {
        try {
            String sql = " SELECT * FROM USUARIO WHERE USUARIO = ? ";
            return jdbcTemplate.queryForObject(sql, usuarioMapper, usuario);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }

    }

    @Override
    public List<String> getRolesByUsuarioId(UUID idUsuario) {
        try {
            String sql = "SELECT R.NOMBRE " +
                    "FROM ROL R INNER JOIN USUARIO_ROL UR " +
                    "ON R.ID_ROL = UR.ID_ROL " +
                    "WHERE UR.ID_USUARIO = ? ";
            return jdbcTemplate.queryForList(sql, String.class, idUsuario.toString());
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

    @Override
    public List<Map<String, Object>> findAllUsuarioDTO(int page, int size) {
        try {
            int offset = (page - 1) * size;
            String sql = "SELECT U.ID_USUARIO AS ID_USUARIO, U.USUARIO AS USUARIO, U.PASSWORD AS PASSWORD, U.EMAIL AS EMAIL , U.ACTIVO AS ACTIVO, U.FEC_CREACION AS FEC_CREACION, "
                    +
                    "P.DNI AS DNI, P.NOMBRE AS NOMBRE, P.APE_PATERNO AS APE_PATERNO, P.APE_MATERNO AS APE_MATERNO, P.SEXO AS SEXO, P.TELEFONO AS TELEFONO, P.DIRECCION AS DIRECCION, P.ACTIVO AS ACTIVO, "
                    +
                    "R.ID_ROL AS ID_ROL, R.NOMBRE AS NOMBRE, R.DETALLE AS DETALLE, R.FEC_CREACION AS FEC_CREACION, R.ACTIVO AS ACTIVO "
                    +
                    "FROM USUARIO U " +
                    "INNER JOIN PERSONA P ON U.ID_USUARIO = P.ID_USUARIO " +
                    "INNER JOIN USUARIO_ROL UR ON U.ID_USUARIO = UR.ID_USUARIO " +
                    "INNER JOIN ROL R ON UR.ID_ROL = R.ID_ROL WHERE U.ACTIVO=true\n" + //
                    "AND P.ACTIVO=true\n" + //
                    "AND R.ACTIVO = true ORDER BY U.ID_USUARIO DESC LIMIT ? OFFSET ?";

            Map<UUID, Map<String, Object>> agrupadoPorUsuario = new LinkedHashMap<>();

            jdbcTemplate.query(sql, new Object[]{size, offset}, (rs) -> {

                UUID idUsuario = UUID.fromString(rs.getString(1)); // id_usuario

                Map<String, Object> registro = agrupadoPorUsuario.get(idUsuario);

                if (registro == null) {
                    Map<String, Object> usuario = new HashMap<>();
                    usuario.put("ID_USUARIO", UUID.fromString(rs.getString(1)));
                    usuario.put("USUARIO", rs.getString(2));
                    usuario.put("EMAIL", rs.getString(4));
                    usuario.put("ACTIVO", rs.getBoolean(5));
                    usuario.put("FEC_CREACION", rs.getTimestamp(6));

                    Map<String, Object> persona = new HashMap<>();
                    persona.put("DNI", rs.getString(7));
                    persona.put("NOMBRE", rs.getString(8));
                    persona.put("APE_PATERNO", rs.getString(9));
                    persona.put("APE_MATERNO", rs.getString(10));
                    persona.put("SEXO", rs.getString(11));
                    persona.put("TELEFONO", rs.getString(12));
                    persona.put("DIRECCION", rs.getString(13));
                    persona.put("ACTIVO", rs.getBoolean(14));

                    registro = new HashMap<>();
                    registro.put("usuario", usuario);
                    registro.put("persona", persona);
                    registro.put("roles", new ArrayList<Map<String, Object>>());

                    agrupadoPorUsuario.put(idUsuario, registro);
                }

                List<Map<String, Object>> roles = (List<Map<String, Object>>) registro.get("roles");

                Map<String, Object> rol = new HashMap<>();
                rol.put("ID_ROL", UUID.fromString(rs.getString(15)));
                rol.put("NOMBRE", rs.getString(16));
                rol.put("DETALLE", rs.getString(17));
                rol.put("FEC_CREACION", rs.getTimestamp(18));
                rol.put("ACTIVO", rs.getBoolean(19));

                roles.add(rol);
            });
            return new ArrayList<>(agrupadoPorUsuario.values());
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

    @Override
    public Map<String, Object> findAllUsuariosByPersonaId(UUID idUsuario) {
        try {
            String sql = "SELECT U.ID_USUARIO AS ID_USUARIO, U.USUARIO AS USUARIO, U.PASSWORD AS PASSWORD, U.EMAIL AS EMAIL , U.ACTIVO AS ACTIVO, U.FEC_CREACION AS FEC_CREACION, "
                    +
                    "P.DNI AS DNI, P.NOMBRE AS NOMBRE, P.APE_PATERNO AS APE_PATERNO, P.APE_MATERNO AS APE_MATERNO, P.SEXO AS SEXO, P.TELEFONO AS TELEFONO, P.DIRECCION AS DIRECCION, P.ACTIVO AS ACTIVO, "
                    +
                    "R.ID_ROL AS ID_ROL, R.NOMBRE AS NOMBRE, R.DETALLE AS DETALLE, R.FEC_CREACION AS FEC_CREACION, R.ACTIVO AS ACTIVO "
                    +
                    "FROM USUARIO U " +
                    "INNER JOIN PERSONA P ON U.ID_USUARIO = P.ID_USUARIO " +
                    "INNER JOIN USUARIO_ROL UR ON U.ID_USUARIO = UR.ID_USUARIO " +
                    "INNER JOIN ROL R ON UR.ID_ROL = R.ID_ROL WHERE U.ACTIVO=true\n" + //
                    "AND P.ACTIVO=true\n" + //
                    "AND R.ACTIVO = true AND P.ID_USUARIO = ?";

            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> roles = new ArrayList<>();

            jdbcTemplate.query(sql, new Object[]{idUsuario.toString()}, (rs) -> {

                if (result.isEmpty()) {
                    Map<String, Object> usuario = new HashMap<>();
                    usuario.put("ID_USUARIO", UUID.fromString(rs.getString(1)));
                    usuario.put("USUARIO", rs.getString(2));
                    usuario.put("EMAIL", rs.getString(4));
                    usuario.put("ACTIVO", rs.getBoolean(5));
                    usuario.put("FEC_CREACION", rs.getTimestamp(6));

                    Map<String, Object> persona = new HashMap<>();
                    persona.put("DNI", rs.getString(7));
                    persona.put("NOMBRE", rs.getString(8));
                    persona.put("APE_PATERNO", rs.getString(9));
                    persona.put("APE_MATERNO", rs.getString(10));
                    persona.put("SEXO", rs.getString(11));
                    persona.put("TELEFONO", rs.getString(12));
                    persona.put("DIRECCION", rs.getString(13));
                    persona.put("ACTIVO", rs.getBoolean(14));

                    result.put("usuario", usuario);
                    result.put("persona", persona);
                    result.put("roles", roles);
                }

                Map<String, Object> rol = new HashMap<>();

                rol.put("ID_ROL", UUID.fromString(rs.getString(15)));
                rol.put("NOMBRE", rs.getString(16));
                rol.put("DETALLE", rs.getString(17));
                rol.put("FEC_CREACION", rs.getTimestamp(18));
                rol.put("ACTIVO", rs.getBoolean(19));

                roles.add(rol);
            });
            return result;
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

    @Override
    @Transactional
    public void insertUsuarioByUsuario(UsuarioDTO usuarioDTO) {
        try {
            Usuario user = new Usuario();
            user = usuarioDTO.getUsuario();

            Persona per = new Persona();
            per = usuarioDTO.getPersona();

            List<Rol> roles = new ArrayList<>();
            roles = usuarioDTO.getRol();

            String sqlUsuario = "INSERT INTO BD_EC_CLOTHING.USUARIO (ID_USUARIO, USUARIO, PASSWORD, EMAIL, ACTIVO, FEC_CREACION) VALUES ( ? , ? , ? , ? , ? , ? ) ";
            jdbcTemplate.update(sqlUsuario, user.getIdUsuario().toString(), user.getUsuario(), user.getPassword(),
                    user.getEmail(), user.getActivo(),
                    user.getFecCreacion());

            String sqlPersona = "INSERT INTO BD_EC_CLOTHING.PERSONA (DNI, ID_USUARIO, NOMBRE, APE_PATERNO, APE_MATERNO, SEXO, TELEFONO, DIRECCION, ACTIVO) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
            jdbcTemplate.update(sqlPersona, per.getDni(), user.getIdUsuario().toString(),
                    per.getNombre(), per.getApePaterno(),
                    per.getApeMaterno(), per.getSexo(),
                    per.getTelefono(), per.getDireccion(),
                    per.getActivo());

            String sqlUsuarioRol = "INSERT INTO BD_EC_CLOTHING.USUARIO_ROL (ID_USUARIO, ID_ROL) VALUES ( ? , ? ) ";
            for (Rol rol : roles) {
                jdbcTemplate.update(sqlUsuarioRol, user.getIdUsuario().toString(), rol.getIdRol().toString());
            }
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al insertar el usuario en la base de datos", ex);
        } catch (DuplicateKeyException ex) {
            throw new DuplicateKeyException("Dato duplicado", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al insertar el usuario en la base de datos", ex);
        }
    }

    @Override
    @Transactional
    public Usuario updateUsuarioByUsuario(UsuarioDTO usuarioDTO) {
        try {
            Usuario user = usuarioDTO.getUsuario();
            Persona per = usuarioDTO.getPersona();
            List<Rol> roles = usuarioDTO.getRol();

            // Actualizar USUARIO
            String sqlUsuario = "UPDATE BD_EC_CLOTHING.USUARIO SET PASSWORD = ?, EMAIL = ?, ACTIVO = ? WHERE ID_USUARIO = ?";
            jdbcTemplate.update(sqlUsuario, user.getPassword(), user.getEmail(), user.getActivo(),
                    user.getIdUsuario().toString());

            // Actualizar PERSONA
            String sqlPersona = "UPDATE BD_EC_CLOTHING.PERSONA SET NOMBRE = ?, APE_PATERNO = ?, APE_MATERNO = ?, SEXO = ?, TELEFONO = ?, DIRECCION = ?, ACTIVO = ? WHERE DNI = ? AND ID_USUARIO = ?";
            jdbcTemplate.update(sqlPersona, per.getNombre(), per.getApePaterno(), per.getApeMaterno(), per.getSexo(),
                    per.getTelefono(), per.getDireccion(), per.getActivo(), per.getDni(),
                    user.getIdUsuario().toString());

            // Actualizar ROLES: eliminar roles actuales y agregar los nuevos
            String sqlDeleteRoles = "DELETE FROM BD_EC_CLOTHING.USUARIO_ROL WHERE ID_USUARIO = ?";
            jdbcTemplate.update(sqlDeleteRoles, user.getIdUsuario().toString());

            String sqlInsertRol = "INSERT INTO BD_EC_CLOTHING.USUARIO_ROL (ID_USUARIO, ID_ROL) VALUES (?, ?)";
            for (Rol rol : roles) {
                jdbcTemplate.update(sqlInsertRol, user.getIdUsuario().toString(), rol.getIdRol().toString());
            }

            return user; // Retornar el usuario actualizado

        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al insertar el usuario en la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al insertar el usuario en la base de datos", ex);
        }
    }

    @Override
    @Transactional
    public void deleteUsuarioByUsuario(UUID idUsuario) {
        try {
            // Eliminar roles asociados
            String sqlDeleteRoles = "DELETE FROM BD_EC_CLOTHING.USUARIO_ROL WHERE ID_USUARIO = ?";
            jdbcTemplate.update(sqlDeleteRoles, idUsuario.toString());

            // Eliminar persona asociada
            String sqlDeletePersona = "DELETE FROM BD_EC_CLOTHING.PERSONA WHERE ID_USUARIO = ?";
            jdbcTemplate.update(sqlDeletePersona, idUsuario.toString());

            // Eliminar usuario
            String sqlDeleteUsuario = "DELETE FROM BD_EC_CLOTHING.USUARIO WHERE ID_USUARIO = ?";
            jdbcTemplate.update(sqlDeleteUsuario, idUsuario.toString());
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al insertar el usuario en la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al insertar el usuario en la base de datos", ex);
        }
    }

    @Override
    public Usuario findUsuarioById(UUID idUsuario) {
        try {
            String sql = " SELECT * FROM USUARIO WHERE ID_USUARIO = ? ";
            return jdbcTemplate.queryForObject(sql, usuarioMapper, idUsuario.toString());
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

}