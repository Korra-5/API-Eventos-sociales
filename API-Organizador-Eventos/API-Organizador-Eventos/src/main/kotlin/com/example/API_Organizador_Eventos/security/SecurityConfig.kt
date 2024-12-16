package com.example.API_Organizador_Eventos.security

import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var rsaKeys: RSAKeysProperties

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->

                auth.requestMatchers("/usuarios/login").permitAll()
                auth.requestMatchers("/usuarios/signup").permitAll()
                auth.requestMatchers(HttpMethod.DELETE, "/usuarios/deleteUsuario").authenticated()
                auth.requestMatchers("/comunidad/getComunidad").permitAll()
                auth.requestMatchers("/actividad/getActividad").permitAll()

                auth.requestMatchers("/actividad/crearActividad")
                    .hasRole("ADMIN")
                auth.requestMatchers("/comunidad/crearComunidad")
                    .hasRole("ADMIN")
                auth.requestMatchers(HttpMethod.DELETE, "/comunidad/deleteComunidad")
                    .hasRole("ADMIN")
                auth.requestMatchers(HttpMethod.DELETE, "/actividad/deleteActividad")
                    .hasRole("ADMIN")
                auth.requestMatchers(HttpMethod.PUT, "/comunidad/updateComunidad")
                    .hasRole("ADMIN")
                auth.requestMatchers(HttpMethod.PUT, "/actividad/updateActividad")
                    .hasRole("ADMIN")
            }
            .oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults()) }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic(Customizer.withDefaults())
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.accessDeniedHandler { request, response, accessDeniedException ->
                    response.status = HttpStatus.FORBIDDEN.value()
                    response.writer.write("Acceso denegado. No tienes permisos de ADMIN.")
                }
            }
            .build()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration) : AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }


    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk: JWK = RSAKey.Builder(rsaKeys.publicKey).privateKey(rsaKeys.privateKey).build()
        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }


    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey).build()
    }


}