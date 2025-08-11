package com.clothing.ecommerce.controller;

import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ControllerException;
import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.service.IPersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${persona.controller.path}")
@AllArgsConstructor
public class PersonaController {

    @Autowired
    private final IPersonaService personaService;


    @GetMapping("${persona.controller.findPersona}/dni/{dni}")
    public ResponseEntity<Persona> findPersonaByDni(@PathVariable("dni") String dni) {
        try {
            Persona personaConsulta = personaService.findPersonaByDni(dni);
            if (personaConsulta == null) {
                return ResponseEntity.ok(new Persona());
            }
            return ResponseEntity.ok(personaConsulta);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el controller", e);
        }
    }

}
