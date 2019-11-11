package br.com.project.config;

import br.com.project.converter.ProjectAccessTokenConverter;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.jwt.file-name}")
    private String fileName;
    @Value("${security.oauth2.resource.jwt.file-password}")
    private String filePassword;
    @Value("${security.oauth2.resource.jwt.key-pair-name}")
    private String keyPairName;
    @Value("${security.oauth2.resource.jwt.key-pair-password}")
    private String keyPairPassword;
    @Autowired
    private ProjectAccessTokenConverter accessTokenConverter;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
        config.resourceId(null);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(accessTokenConverter);
        Resource resource = resourceLoader.getResource(fileName);

        if (StringUtils.isBlank(filePassword)) {
            throw new IllegalArgumentException("The value security.oauth2.resource.jwt.file-password cannot be null or empty");
        }
        char[] passwordArray = filePassword.toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, passwordArray);

        if (StringUtils.isBlank(keyPairPassword)) {
            throw new IllegalArgumentException("The value security.oauth2.resource.jwt.key-pair-password cannot be null or empty");
        }
        char[] keyPairPasswordArray = keyPairPassword.toCharArray();
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyPairName, keyPairPasswordArray);

        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
