package com.clothing.ecommerce.service;

import java.util.List;
import com.clothing.ecommerce.model.entity.Modulo;

public interface IModuloService {

    List<Modulo> findModulosByUsuario(String usuario);

}
