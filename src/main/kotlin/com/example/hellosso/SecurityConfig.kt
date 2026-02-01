package com.example.hellosso

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

@Configuration
@EnableWebSecurity
open class SecurityConfig {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize.anyRequest().authenticated()
            }
            .oauth2Login(Customizer.withDefaults()) // enables the /login redirect → Google OAuth flow
            // your normal security config first (oauth2ResourceServer, formLogin, etc.)
            // AuthorizationFilter is last by default, so "after" means truly post-authz. :contentReference[oaicite:2]{index=2}
            .addFilterAfter(ProxyingRequestFilter(), AuthorizationFilter::class.java)

        return http.build()
    }
}
