package com.clothing.ecommerce.repository.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.RepositoryException;
import com.clothing.ecommerce.model.entity.Modulo;
import com.clothing.ecommerce.model.rowMapper.ModuloRowMapper;
import com.clothing.ecommerce.repository.IModuloRepository;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ModuloRepositoryImpl implements IModuloRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ModuloRowMapper moduloRowMapper = new ModuloRowMapper();

    @Override
    public Modulo findModuloById(UUID idModulo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findModuloById'");
    }

    @Override
    public Modulo findModuloByNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findModuloByNombre'");
    }

    @Override
    public Modulo findModuloByUrl(String url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findModuloByUrl'");
    }

    @Override
    public List<Modulo> findAllModulos(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllModulos'");
    }

    @Override
    public void insertModulo(Modulo modulo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertModulo'");
    }

    @Override
    public Modulo updateModulo(Modulo modulo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateModulo'");
    }

    @Override
    public void deleteModulo(UUID idModulo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteModulo'");
    }

    @Override
    public List<Modulo> findModulosByUsuario(String usuario) {
        try {
            String sql = " SELECT * FROM MODULO M INNER JOIN ROL_MODULO RM ON RM.ID_MODULO = M.ID_MODULO INNER JOIN ROL R ON R.ID_ROL = RM.ID_ROL INNER JOIN USUARIO_ROL UR ON UR.ID_ROL = RM.ID_ROL INNER JOIN USUARIO U ON U.ID_USUARIO = UR.ID_USUARIO WHERE USUARIO = ? ";
            return (List<Modulo>) jdbcTemplate.query(sql, moduloRowMapper, usuario);
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

}
