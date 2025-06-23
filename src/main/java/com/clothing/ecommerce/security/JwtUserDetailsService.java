package com.clothing.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.repository.IUsuarioRepository;
import lombok.AllArgsConstructor;

//Clase S4
@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private final IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = iUsuarioRepository.findUsuariosByUsuario(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new JwtUserDetails(user, iUsuarioRepository);
    }

}
