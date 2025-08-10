package com.clothing.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clothing.ecommerce.exception.AppException;
import com.clothing.ecommerce.exception.capas.ServiceException;
import com.clothing.ecommerce.exception.capas.ValidationException;
import com.clothing.ecommerce.model.dto.UsuarioDTO;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.repository.IUsuarioRepository;
import com.clothing.ecommerce.service.IUsuarioService;
import com.clothing.ecommerce.util.ManualValidatorUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final IUsuarioRepository usuarioRepository;

    @Override
    public List<Map<String, Object>> findAllUsuarioDTO(int page, int size) {
        try {
            List<Map<String, Object>> usuarioList = usuarioRepository.findAllUsuarioDTO(page, size);
            if (usuarioList == null || usuarioList.isEmpty()) {
                throw new NotFoundException();
            }
            return usuarioList;
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

    @Override
    public void insertUsuarioByUsuario(UsuarioDTO usuarioDTO) {
        try {
            List<String> validationErrorsGeneral = new ArrayList<>();
            List<String> validationErrorsUsuario = ManualValidatorUtil.validatorUsuario(usuarioDTO.getUsuario());
            List<String> validationErrorsPersona = ManualValidatorUtil.validatorPersona(usuarioDTO.getPersona());

            if (validationErrorsUsuario.size() > 0) {
                validationErrorsGeneral.add(validationErrorsUsuario.toString());
                throw new ValidationException("Validaciones de Request: " +
                        String.join(" ", validationErrorsGeneral));
            }
            if (validationErrorsPersona.size() > 0) {
                validationErrorsGeneral.add(validationErrorsPersona.toString());
                throw new ValidationException("Validaciones de Request: " +
                        String.join(" ", validationErrorsGeneral));
            }
            String encodedPassword = passwordEncoder.encode(usuarioDTO.getUsuario().getPassword());
            UUID UsuarioKey = UUID.randomUUID();
            usuarioDTO.getUsuario().setPassword(encodedPassword);
            usuarioDTO.getUsuario().setIdUsuario(UsuarioKey);
            usuarioRepository.insertUsuarioByUsuario(usuarioDTO);
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }

    @Override
    public Usuario updateUsuarioByUsuario(UsuarioDTO usuarioDTO) {
        try {
            List<String> validationErrorsGeneral = new ArrayList<>();
            List<String> validationErrorsUsuario = ManualValidatorUtil.validatorUsuario(usuarioDTO.getUsuario());
            List<String> validationErrorsPersona = ManualValidatorUtil.validatorPersona(usuarioDTO.getPersona());

            String encodedPassword = passwordEncoder.encode(usuarioDTO.getUsuario().getPassword());
            usuarioDTO.getUsuario().setPassword(encodedPassword);

            if (validationErrorsUsuario.size() > 0) {
                validationErrorsGeneral.add(validationErrorsUsuario.toString());
                throw new ValidationException("Validaciones de Request: " +
                        String.join(" ", validationErrorsGeneral));
            }
            if (validationErrorsPersona.size() > 0) {
                validationErrorsGeneral.add(validationErrorsPersona.toString());
                throw new ValidationException("Validaciones de Request: " +
                        String.join(" ", validationErrorsGeneral));
            }
            Usuario existingUsuario = usuarioRepository.updateUsuarioByUsuario(usuarioDTO);
            existingUsuario.setPassword(""); // No retornar la contraseña en la respuesta
            return existingUsuario;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }

    @Override
    public void deleteUsuarioByUsuario(UUID idUsuario) {
        try {
            usuarioRepository.deleteUsuarioByUsuario(idUsuario);
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }

    @Override
    public Usuario findUsuarioById(UUID idUsuario) {
        try {
            Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
            if (usuario == null) {
                throw new NotFoundException();
            }
            usuario.setPassword(""); // No retornar la contraseña en la respuesta
            return usuario;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }

    }

    @Override
    public Map<String, Object> findAllUsuariosByPersonaId(UUID idPersona) {
        try {
            Map<String, Object> usuarioMap = usuarioRepository.findAllUsuariosByPersonaId(idPersona);
            if (usuarioMap == null || usuarioMap.isEmpty()) {
                throw new NotFoundException();
            }
            return usuarioMap;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }

    }

    @Override
    public Usuario findUsuariosByUsuario(String usuario) {
        try {
            Usuario existingUsuario = usuarioRepository.findUsuariosByUsuario(usuario);
            if (existingUsuario == null) {
                return null;
            }
            existingUsuario.setPassword(null);
            return existingUsuario;
        } catch (ServiceException e) {
            throw new ServiceException("Error en la lógica de negocio", e);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error en la capa service", e);
        }
    }

}
