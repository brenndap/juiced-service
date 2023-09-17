package com.example.juiced

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JuicedApplication

fun main(args: Array<String>) {
    runApplication<JuicedApplication>(*args)
}
