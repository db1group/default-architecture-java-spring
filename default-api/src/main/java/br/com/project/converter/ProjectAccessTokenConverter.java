package br.com.project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProjectAccessTokenConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Autowired
    public ProjectAccessTokenConverter(ProjectUserAuthenticationConverter converter) {
        super.setUserTokenConverter(converter);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication result = super.extractAuthentication(map);
        result.setDetails(map);
        return result;
    }

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

}
