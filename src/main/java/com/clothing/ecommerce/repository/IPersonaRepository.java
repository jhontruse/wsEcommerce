package com.clothing.ecommerce.repository;

import com.clothing.ecommerce.model.entity.Persona;

public interface IPersonaRepository {

    Persona findPersonaByDni(String dni);

}
