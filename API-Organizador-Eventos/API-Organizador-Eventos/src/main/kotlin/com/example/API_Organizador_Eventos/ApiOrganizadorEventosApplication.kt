package com.example.API_Organizador_Eventos

import com.example.API_Organizador_Eventos.security.RSAKeysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(RSAKeysProperties::class)
class ApiOrganizadorEventosApplication

fun main(args: Array<String>) {
	runApplication<ApiOrganizadorEventosApplication>(*args)
}
