package com.clothing.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ControllerException;
import com.clothing.ecommerce.model.entity.Modulo;
import com.clothing.ecommerce.service.IModuloService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("${modulo.controller.path}")
@AllArgsConstructor
public class ModuloController {

    @Autowired
    private final IModuloService moduloService;

    //@PreAuthorize("@authorizeLogic.hasAccess('findModuloByUsuario')")
    @GetMapping("${modulo.controller.findModuloByUsuario}/{usuario}")
    public ResponseEntity<List<Modulo>> findModuloByUsuario(@PathVariable("usuario") String usuario) {
        try {
            System.out.println("Usuario recibido: " + usuario);
            List<Modulo> modulos = moduloService.findModulosByUsuario(usuario);
            return ResponseEntity.ok(modulos);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el controller", e);
        }
    }

}
