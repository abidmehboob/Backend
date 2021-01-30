package com.alseyahat.app.security.config;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.alseyahat.app.security.service.CustomAuthDetailsService;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@Deprecated
@EnableAuthorizationServer
@FieldDefaults(level = AccessLevel.PRIVATE)
@EnableConfigurationProperties(value = {JwtConfigProperties.class})
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomAuthDetailsService customAuthDetailsService;


    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        final JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        if (CollectionUtils.isNotEmpty(jdbcClientDetailsService.listClientDetails())) {
            jdbcClientDetailsService.listClientDetails().forEach(c -> jdbcClientDetailsService.removeClientDetails(c.getClientId()));
        }
        clients.jdbc(dataSource)
                .withClient(jwtConfigProperties.getEmployee().getClientId())
                .secret(passwordEncoder.encode(jwtConfigProperties.getEmployee().getClientSecret()))
                .accessTokenValiditySeconds(Integer.parseInt(jwtConfigProperties.getEmployee().getAccessTokenValiditySeconds()))
                .refreshTokenValiditySeconds(Integer.parseInt(jwtConfigProperties.getEmployee().getRefreshTokenValiditySeconds()))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("write", "read", "trust")
                .and()
                .withClient(jwtConfigProperties.getCustomer().getClientId())
                .secret(passwordEncoder.encode(jwtConfigProperties.getCustomer().getClientSecret()))
                .accessTokenValiditySeconds(Integer.parseInt(jwtConfigProperties.getCustomer().getAccessTokenValiditySeconds()))
                .refreshTokenValiditySeconds(Integer.parseInt(jwtConfigProperties.getCustomer().getRefreshTokenValiditySeconds()))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("write", "read", "trust");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(authenticationTokenEnhancer())
                .userDetailsService(customAuthDetailsService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(authenticationTokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter authenticationTokenEnhancer() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new AuthenticationTokenEnhancer();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    private KeyPair keyPair() {
        return keyStoreKeyFactory().getKeyPair(jwtConfigProperties.getSecurity().getKeyPairAlias(), jwtConfigProperties.getSecurity().getKeyPairPassword().toCharArray());
    }

    private KeyStoreKeyFactory keyStoreKeyFactory() {
        return new KeyStoreKeyFactory(jwtConfigProperties.getSecurity().getKeyStore(), jwtConfigProperties.getSecurity().getKeyStorePassword().toCharArray());
    }
}
