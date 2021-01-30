package com.alseyahat.app.security.config;

import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.alseyahat.app.commons.Constants.*;
import static java.util.Optional.ofNullable;

public class BearerCookiesTokenExtractor extends BearerTokenExtractor {

    @Override
    protected String extractToken(final HttpServletRequest request) {
        return ofNullable(request.getHeader(USER_AGENT)).filter(userAgent -> !MOBILE.equals(userAgent))
                .map(c -> ofNullable(WebUtils.getCookie(request, TOKEN))
                        .map(Cookie::getValue)
                        .orElse(super.extractToken(request)))
                .orElse(super.extractToken(request));
    }
}
