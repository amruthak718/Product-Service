package com.amrutha.employee_service.confiq;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class RoleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String role = request.getHeader("role");

        if ("admin".equals(role)) {
            PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken("admin", null,
                    List.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
