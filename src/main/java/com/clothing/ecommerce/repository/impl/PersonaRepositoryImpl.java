package com.clothing.ecommerce.repository.impl;

import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.RepositoryException;
import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.model.rowMapper.PersonaRowMapper;
import com.clothing.ecommerce.model.rowMapper.RolRowMapper;
import com.clothing.ecommerce.repository.IPersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PersonaRepositoryImpl implements IPersonaRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PersonaRowMapper personaMapper = new PersonaRowMapper();

    @Override
    public Persona findPersonaByDni(String dni) {
        try {
            String sql = " SELECT * FROM PERSONA WHERE DNI = ? ";
            return jdbcTemplate.queryForObject(sql, personaMapper, dni);
        } catch (EmptyResultDataAccessException ex) {
            Persona persona = null;
            return persona;
        } catch (DataAccessException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (AppException ex) {
            throw new RepositoryException("Error al acceder a la base de datos", ex);
        } catch (Exception ex) {
            throw new RepositoryException("Error inesperado al acceder a la base de datos", ex);
        }
    }

}
