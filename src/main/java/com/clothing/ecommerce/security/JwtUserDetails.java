package com.clothing.ecommerce.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.clothing.ecommerce.model.entity.Usuario;
import com.clothing.ecommerce.repository.IUsuarioRepository;
import lombok.AllArgsConstructor;

//Clase S4.2
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {

    @Autowired
    private final Usuario user;

    @Autowired
    private final IUsuarioRepository iUsuarioRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            Usuario usuario = iUsuarioRepository.findUsuariosByUsuario(user.getUsuario());
            List<GrantedAuthority> roles = new ArrayList<>();
            List<String> roleNames = iUsuarioRepository.getRolesByUsuarioId(usuario.getIdUsuario());
            roleNames.forEach(roleName -> {
                roles.add(new SimpleGrantedAuthority(roleName));
            });
            return roles;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los roles del usuario: " + user.getUsuario(), e);
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
