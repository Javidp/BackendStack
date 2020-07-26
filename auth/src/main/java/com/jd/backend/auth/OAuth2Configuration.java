package com.jd.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.Base64;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthConfiguration authConfiguration;

//    @Autowired
//    private DataSource dataSource;

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource);
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientid")
//                .secret("clientSecret")
                .secret("$2y$12$fCUYWICDKbeBYRelUShtnuDMFjh44mLnvk95BYi8NXhhcAQQHQGz6")
                .scopes("read", "write")
//                .authorizedGrantTypes("password", "refresh_token")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource(authConfiguration.getJwtTokenKeyPath()), authConfiguration.getJwtTokenKeyPassword());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(authConfiguration.getJwtTokenKeyAlias());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        System.out.println("========================");
        System.out.println("private key info:\n" + keyPair.getPrivate());
        System.out.println("public key info:\n" + keyPair.getPublic());

        System.out.println("private key:\n" + privateKey);
        System.out.println("public key:\n" + publicKey);
        System.out.println("========================");

        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(authConfiguration.getJwtTokenKeyAlias()));
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
