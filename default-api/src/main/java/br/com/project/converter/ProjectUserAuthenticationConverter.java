package br.com.project.converter;

import br.com.project.security.GrantedAuthority;
import br.com.project.security.Principal;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProjectUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        List<SimpleGrantedAuthority> authorities = GrantedAuthority.of(map);
        Principal principal = Principal.of(map);
        return new UsernamePasswordAuthenticationToken(principal, Strings.EMPTY, authorities);
    }

}
