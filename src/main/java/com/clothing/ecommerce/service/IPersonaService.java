package com.clothing.ecommerce.service;

import com.clothing.ecommerce.model.entity.Persona;

public interface IPersonaService {

    Persona findPersonaByDni(String dni);

}

