package com.alseyahat.app.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomAuthDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    UserDetails loadUserByUsername(final String username);
}
