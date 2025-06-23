package com.clothing.ecommerce.repository;

import java.util.List;
import java.util.UUID;

import com.clothing.ecommerce.model.entity.Modulo;

public interface IModuloRepository {

    Modulo findModuloById(UUID idModulo);

    Modulo findModuloByNombre(String nombre);

    Modulo findModuloByUrl(String url);

    List<Modulo> findAllModulos(int page, int size);

    void insertModulo(Modulo modulo);

    Modulo updateModulo(Modulo modulo);

    void deleteModulo(UUID idModulo);

    List<Modulo> findModulosByUsuario(String usuario);

}
