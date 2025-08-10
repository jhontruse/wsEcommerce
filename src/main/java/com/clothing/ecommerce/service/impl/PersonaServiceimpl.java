package com.clothing.ecommerce.service.impl;

import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ServiceException;
import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.repository.IPersonaRepository;
import com.clothing.ecommerce.repository.IUsuarioRepository;
import com.clothing.ecommerce.service.IPersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaServiceimpl implements IPersonaService {

    @Autowired
    private final IPersonaRepository personaRepository;

    @Override
    public Persona findPersonaByDni(String dni) {
        try {
            Persona existingPersona = personaRepository.findPersonaByDni(dni);
            return existingPersona;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }
}
