package com.clothing.ecommerce.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

//Clase S8
@Component
@AllArgsConstructor
public class AuthorizeLogic {

    public boolean hasAccess(String path) {
        boolean result = false;

        String methodRole = switch (path) {
            case "findAllUsuarios", "createUsuario", "updateUsuario", "deleteUsuario", "findUsuarioById",
                 "findUsuario", "findModuloByUsuario", "findUsuarioByUsu" -> "ADMIN";
            case "findById", "getBydId" -> "USER,DBA";
            default -> "ROOT";
        };

        String methodRoles[] = methodRole.split(",");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
            String roleUser = grantedAuthority.getAuthority();
            // log.info("Role is: " + roleUser);

            for (String role : methodRoles) {
                if (roleUser.equalsIgnoreCase(role)) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

}
