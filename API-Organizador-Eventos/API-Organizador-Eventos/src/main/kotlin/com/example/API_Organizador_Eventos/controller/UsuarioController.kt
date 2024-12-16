package com.example.API_Organizador_Eventos.controller

import com.example.API_Organizador_Eventos.model.Usuario
import com.example.API_Organizador_Eventos.service.TokenService
import com.example.API_Organizador_Eventos.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenService: TokenService

    @PostMapping("/login")
    fun login(@RequestBody usuario: Usuario): ResponseEntity<Any>? {
        println(usuario)
        val authentication: Authentication
        try {
            authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(usuario.username, usuario.password))
            println(authentication.details)
        } catch (e: AuthenticationException) {
            return ResponseEntity(mapOf("mensaje" to "Credenciales incorrectas"), HttpStatus.UNAUTHORIZED)
        }

        val token = tokenService.generarToken(authentication)

        return ResponseEntity(
            mapOf(
                "mensaje" to "Login exitoso",
                "token" to token
            ),
            HttpStatus.OK
        )
    }

    @PostMapping("/signup")
    fun signup(@RequestBody usuario: Usuario): ResponseEntity<Any>? {
        return usuarioService.registrarUsuario(usuario)
    }

    @DeleteMapping("/deleteUsuario")
    fun deleteUsuario(@RequestBody id: Long): ResponseEntity<Any>? {
        val usuario = usuarioService.deleteUsuario(id)
        return ResponseEntity(usuario, HttpStatus.OK)
    }
}
