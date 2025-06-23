package com.clothing.ecommerce.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ControllerException;
import com.clothing.ecommerce.model.dto.UsuarioDTO;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.service.IUsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("${usuario.controller.path}")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final IUsuarioService usuarioService;

    @PreAuthorize("@authorizeLogic.hasAccess('findAllUsuarios')")
    @GetMapping("${usuario.controller.findAllUsuario}")
    public ResponseEntity<List<Map<String, Object>>> findAllUsuarios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Map<String, Object>> list = usuarioService.findAllUsuarioDTO(page, size);
            return ResponseEntity.ok(list);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el controller", e);
        }
    }

    @PreAuthorize("@authorizeLogic.hasAccess('createUsuario')")
    @PostMapping("${usuario.controller.createUsuario}")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.insertUsuarioByUsuario(usuarioDTO);
            return ResponseEntity.ok(usuarioDTO);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el la capa controller", e);
        }
    }

    @PreAuthorize("@authorizeLogic.hasAccess('updateUsuario')")
    @PutMapping("${usuario.controller.updateUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.updateUsuarioByUsuario(usuarioDTO);
            return ResponseEntity.ok(usuarioDTO.getUsuario());
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el la capa controller", e);
        }
    }

    @PreAuthorize("@authorizeLogic.hasAccess('deleteUsuario')")
    @DeleteMapping("${usuario.controller.deleteUsuario}/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("idUsuario") UUID idUsuario) {
        try {
            usuarioService.deleteUsuarioByUsuario(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el la capa controller", e);
        }
    }

    @PreAuthorize("@authorizeLogic.hasAccess('findUsuarioById')")
    @GetMapping("${usuario.controller.findUsuarioById}/{idUsuario}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable("idUsuario") UUID idUsuario) {
        try {
            Usuario usuario = usuarioService.findUsuarioById(idUsuario);
            return ResponseEntity.ok(usuario);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el controller", e);
        }
    }

    @PreAuthorize("@authorizeLogic.hasAccess('findUsuario')")
    @GetMapping("${usuario.controller.findUsuario}/{idUsuario}")
    public ResponseEntity<Map<String, Object>> findUsuario(@PathVariable("idUsuario") UUID idUsuario) {
        try {
            Map<String, Object> list = usuarioService.findAllUsuariosByPersonaId(idUsuario);
            return ResponseEntity.ok(list);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ControllerException("Error en el controller", e);
        }
    }

}
