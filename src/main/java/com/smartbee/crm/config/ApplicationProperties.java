package com.smartbee.crm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public static class Security {
        public static class Authentication {
            public static class Jwt {
                private String secret;
                private long tokenValidityInSeconds = 1800L;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long validityInSeconds) {
                    this.tokenValidityInSeconds = validityInSeconds;
                }
            }

            private Jwt jwt;

            public Jwt getJwt() {
                return jwt;
            }

            public void setJwt(Jwt jwt) {
                this.jwt = jwt;
            }
        }

        private Authentication authentication;

        public Authentication getAuthentication() {
            return authentication;
        }

        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

    private Security security;

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
