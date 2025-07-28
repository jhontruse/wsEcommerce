package com.clothing.ecommerce.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.clothing.ecommerce.model.entity.Persona;
import com.clothing.ecommerce.model.entity.Usuario;

public class ManualValidatorUtil {

    public static List<String> validatorUsuario(Usuario usuario) {

        final Pattern VALIDATOR_USUARIO = Pattern.compile("^[a-zA-Z0-9]+$");
        final Pattern VALIDATOR_EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        final Pattern VALIDATOR_PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])[^\\s]{5,}$");
        final Pattern VALIDATOR_ACTIVO = Pattern.compile("^(true|false)$");

        List<String> errors = new ArrayList<>();

        if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
            errors.add("El usuario no puede ser nulo o vacío.");
        }

        if (usuario.getUsuario().length() < 4) {
            errors.add("El usuario debe tener al menos 4 caracteres.");
        }

        if (!VALIDATOR_USUARIO.matcher(usuario.getUsuario()).matches()) {
            errors.add("El usuario solo puede contener letras y números.");
        }

        if (usuario.getUsuario().length() > 100) {
            errors.add("El usuario no debe tener mas de 100 caracteres.");
        }

        // Validación del email
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            errors.add("El email no puede ser nulo o vacío.");
        }

        if (usuario.getEmail().length() < 3) {
            errors.add("El email debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_EMAIL.matcher(usuario.getEmail()).matches()) {
            errors.add("El email no cumple con el formato.");
        }

        if (usuario.getEmail().length() > 250) {
            errors.add("El email no debe tener mas de 250 caracteres.");
        }

        // Validación de la contraseña
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            errors.add("El password no puede ser nulo o vacío.");
        }

        if (usuario.getPassword().length() < 5) {
            errors.add("El password debe tener al menos 5 caracteres.");
        }

        if (!VALIDATOR_PASSWORD.matcher(usuario.getPassword()).matches()) {
            errors.add("El password no cumple con el formato.");
        }

        if (usuario.getPassword().length() > 800) {
            errors.add("El password no debe tener mas de 800 caracteres.");
        }

        // Validación del campo activo
        if (usuario.getActivo() == null) {
            errors.add("El activo no puede ser nulo o vacío.");
        }

        if (!VALIDATOR_ACTIVO.matcher(String.valueOf(usuario.getActivo())).matches()) {
            errors.add("El activo no cumple con el formato.");
        }

        return errors;

    }

    public static List<String> validatorPersona(Persona persona) {

        final Pattern VALIDATOR_DNI = Pattern.compile("^\\d{8}$");
        final Pattern VALIDATOR_NOMBRE = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
        final Pattern VALIDATOR_APEPATERNO = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
        final Pattern VALIDATOR_APEMATERNO = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
        final Pattern VALIDATOR_SEXO = Pattern.compile("^[MFmf]$");
        final Pattern VALIDATOR_TELEFONO = Pattern.compile("^[0-9]+$");
        final Pattern VALIDATOR_DIRECCION = Pattern.compile("^[a-zA-Z0-9.#\\-_\\s]+$");
        final Pattern VALIDATOR_ACTIVO = Pattern.compile("^(true|false)$");

        List<String> errors = new ArrayList<>();

        // Validación del campo dni
        if (persona.getDni() == null || persona.getDni().isEmpty()) {
            errors.add("El dni no puede ser nulo o vacío.");
        }

        if (persona.getDni().length() != 8) {
            errors.add("El dni debe tener 8 caracteres.");
        }

        if (!VALIDATOR_DNI.matcher(persona.getDni()).matches()) {
            errors.add("El dni solo puede contener números.");
        }

        // Validación del campo activo
        if (persona.getActivo() == null) {
            errors.add("El activo no puede ser nulo o vacío.");
        }

        if (!VALIDATOR_ACTIVO.matcher(String.valueOf(persona.getActivo())).matches()) {
            errors.add("El activo no cumple con el formato.");
        }

        // Validación del campo nombre
        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            errors.add("El nombre no puede ser nulo o vacío.");
        }

        if (persona.getNombre().length() < 3) {
            errors.add("El nombre debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_NOMBRE.matcher(persona.getNombre()).matches()) {
            errors.add("El nombre solo puede contener letras.");
        }

        if (persona.getNombre().length() > 250) {
            errors.add("El nombre no debe tener mas de 250 caracteres.");
        }

        // Validación del campo apePaterno
        if (persona.getApePaterno() == null || persona.getApePaterno().isEmpty()) {
            errors.add("El apellido paterno no puede ser nulo o vacío.");
        }

        if (persona.getApePaterno().length() < 3) {
            errors.add("El apellido paterno debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_APEPATERNO.matcher(persona.getApePaterno()).matches()) {
            errors.add("El apellido paterno solo puede contener letras.");
        }

        if (persona.getApePaterno().length() > 250) {
            errors.add("El apellido paterno no debe tener mas de 250 caracteres.");
        }

        // Validación del campo apeMaterno
        if (persona.getApeMaterno() == null || persona.getApeMaterno().isEmpty()) {
            errors.add("El apellido materno no puede ser nulo o vacío.");
        }

        if (persona.getApeMaterno().length() < 3) {
            errors.add("El apellido materno debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_APEMATERNO.matcher(persona.getApeMaterno()).matches()) {
            errors.add("El apellido materno solo puede contener letras.");
        }

        if (persona.getApeMaterno().length() > 250) {
            errors.add("El apellido materno no debe tener mas de 250 caracteres.");
        }

        // Validación del campo apeMaterno
        if (persona.getApeMaterno() == null || persona.getApeMaterno().isEmpty()) {
            errors.add("El apellido materno no puede ser nulo o vacío.");
        }

        if (persona.getApeMaterno().length() < 3) {
            errors.add("El apellido materno debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_APEMATERNO.matcher(persona.getApeMaterno()).matches()) {
            errors.add("El apellido materno solo puede contener letras.");
        }

        if (persona.getApeMaterno().length() > 250) {
            errors.add("El apellido materno no debe tener mas de 250 caracteres.");
        }

        // Validación del campo sexo
        if (persona.getSexo() == null || persona.getSexo().isEmpty()) {
            errors.add("El sexo no puede ser nulo o vacío.");
        }

        if (persona.getSexo().length() != 1) {
            errors.add("El sexo debe tener 1 caracteres.");
        }

        if (!VALIDATOR_SEXO.matcher(persona.getSexo()).matches()) {
            errors.add("El sexo solo puede contener letras.");
        }

        // Validación del campo telefono
        if (persona.getTelefono() == null || persona.getTelefono().isEmpty()) {
            errors.add("El telefono no puede ser nulo o vacío.");
        }

        if (persona.getTelefono().length() != 9) {
            errors.add("El telefono debe tener 9 caracteres.");
        }

        if (!VALIDATOR_TELEFONO.matcher(persona.getTelefono()).matches()) {
            errors.add("El telefono solo puede contener numeros.");
        }

        // Validación del campo direccion
        if (persona.getDireccion() == null || persona.getDireccion().isEmpty()) {
            errors.add("La direccion no puede ser nulo o vacío.");
        }

        if (persona.getDireccion().length() < 3) {
            errors.add("La direccion debe tener al menos 3 caracteres.");
        }

        if (!VALIDATOR_DIRECCION.matcher(persona.getDireccion()).matches()) {
            errors.add("La direccion solo puede contener letras - numeeros y #.");
        }

        if (persona.getDireccion().length() > 250) {
            errors.add("La direccion no debe tener mas de 250 caracteres.");
        }

        return errors;

    }

}
