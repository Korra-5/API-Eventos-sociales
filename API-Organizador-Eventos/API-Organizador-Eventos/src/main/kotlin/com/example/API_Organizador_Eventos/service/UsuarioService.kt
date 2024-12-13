package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.controller.UsuarioController
import com.example.API_Organizador_Eventos.model.Usuario
import com.example.API_Organizador_Eventos.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class UsuarioService : UserDetailsService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Override
    override fun loadUserByUsername(username: String?): UserDetails {
        var usuario: Usuario = usuarioRepository
            .findByUsername(username!!)
            .orElseThrow()

        return User.builder()
            .username(usuario.username)
            .password(usuario.password)
            .roles(usuario.roles)
            .build()
    }


    fun registrarUsuario(usuario: Usuario): ResponseEntity<Any> {
        val username = usuario.username ?: throw IllegalArgumentException("Username is null")

        val existingUser = usuarioRepository.findByUsername(username)

        usuario.password = passwordEncoder.encode(usuario.password)
        usuarioRepository.save(usuario)

        return ResponseEntity(
            mapOf("mensaje" to "Usuario registrado exitosamente", "usuario" to usuario),
            HttpStatus.CREATED
        )
    }

}