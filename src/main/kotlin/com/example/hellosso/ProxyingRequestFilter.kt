package com.example.hellosso

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.kotlin.logger
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.filter.OncePerRequestFilter

class ProxyingRequestFilter : OncePerRequestFilter() {

    val log = logger()

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val shouldProxyRequest = request.requestURI
            .removePrefix(request.contextPath)
            .startsWith("/proxy/")
        return !shouldProxyRequest
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val auth = SecurityContextHolder.getContext().authentication
        val user = auth.principal as OAuth2User
        log.info { "filtering '${request.requestURL}' ; auth ${user.attributes["email"]}" }
    }
}
