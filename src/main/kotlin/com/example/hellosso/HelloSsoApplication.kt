package com.example.hellosso

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HelloSsoApplication

fun main(args: Array<String>) {
    SpringApplication.run(HelloSsoApplication::class.java, *args)
}
