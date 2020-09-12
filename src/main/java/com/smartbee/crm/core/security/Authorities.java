package com.smartbee.crm.core.security;

import org.hibernate.graph.GraphNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Authorities {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String MANAGER = "ROLE_MANAGER";

    public static final String OPERATOR = "ROLE_OPERATOR";

    public static String getRoleAuthority() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .get();
    }
}
