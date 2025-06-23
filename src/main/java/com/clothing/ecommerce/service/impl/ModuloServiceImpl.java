package com.clothing.ecommerce.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ServiceException;
import com.clothing.ecommerce.exception.capas.ValidationException;
import com.clothing.ecommerce.model.entity.Modulo;
import com.clothing.ecommerce.repository.IModuloRepository;
import com.clothing.ecommerce.service.IModuloService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModuloServiceImpl implements IModuloService {

    @Autowired
    private final IModuloRepository moduloRepository;

    @Override
    public List<Modulo> findModulosByUsuario(String usuario) {
        try {
            System.out.println("Usuario recibido en servicio: " + usuario);
            List<Modulo> modulos = moduloRepository.findModulosByUsuario(usuario);
            if (modulos == null || modulos.isEmpty()) {
                throw new NotFoundException();
            }
            return modulos;
        } catch (ValidationException e) {
            throw e;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }

}
