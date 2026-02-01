package com.example.hellosso

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun index(@AuthenticationPrincipal user: OAuth2User): String {
        val email = user.attributes["email"] as String
        return "Hello World $email"
    }
}
