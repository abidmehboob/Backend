package com.alseyahat.app.security.config;

import com.alseyahat.app.security.exception.CustomAccessDeniedHandler;
import com.alseyahat.app.security.exception.CustomAuthenticationEntryPoint;
import com.alseyahat.app.security.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.List;


@Configuration
public class PlancklyResourceServerConfig {

    @Autowired
    @Deprecated
    TokenStore tokenStore;

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    @Deprecated
    public ResourceServerConfiguration plancklyResourceServer() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            @Override
            public void setConfigurers(final List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(final HttpSecurity http) throws Exception {
                http.httpBasic().disable().cors().disable().csrf().disable().formLogin().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authorizeRequests()
                        .antMatchers("/employees/auth/**", "/customers/register-customer/**",
                                "/customers/login", "/profile/forgot-password/**", "/profile/reset-password/**", "/employees/roles",
                                "/oauth/token", "/oauth/check_token/**", "/customers/refresh_token", "/payments/mpgs-pay/acknowledge-payment", "/page/*", "/employees/logout-employee").permitAll()
                        .antMatchers("/baskets/**", "/customers/own-detail/**", "/messages",
                                "/messages/send", "/conversations", "/reviews/**", "/user-vouchers/**",
                                "/complains/**", "/complain-actions/**", "/payments/**",
                                "/accounts/acmob/**", "/branches/nearby-branches/**",
                                "/branches/**", "/categories/**", "/items/**","/cards/**","/vouchers/verify-code")
                        .access("#oauth2.hasScope('read')")
                        .anyRequest()
                        .access("#oauth2.hasScope('write') and #oauth2.hasScope('read') and #oauth2.hasScope('trust')")
                        .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(new CustomAccessDeniedHandler());
                http.addFilterBefore(new CorsFilter(), UsernamePasswordAuthenticationFilter.class);
            }

            @Override
            public void configure(final ResourceServerSecurityConfigurer resources) {
                resources.tokenStore(tokenStore).tokenExtractor(new BearerCookiesTokenExtractor());
            }
        }));
        resource.setOrder(3);
        return resource;
    }
}
