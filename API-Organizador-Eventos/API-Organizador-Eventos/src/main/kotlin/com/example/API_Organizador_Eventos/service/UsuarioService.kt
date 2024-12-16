package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.controller.UsuarioController
import com.example.API_Organizador_Eventos.error.exception.NotFoundException
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
import java.util.*
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

        if (username.length < 5 || username.length > 20) {
            return ResponseEntity(mapOf("mensaje" to "El nombre de usuario debe tener entre 5 y 20 caracteres"), HttpStatus.BAD_REQUEST)
        }

        if (usuarioRepository.existsByUsername(username)) {
            return ResponseEntity(mapOf("mensaje" to "El nombre de usuario ya está en uso"), HttpStatus.BAD_REQUEST)
        }

        val password = usuario.password ?: throw IllegalArgumentException("Password is null")
        if (password.length < 5 || password.length > 20) {
            return ResponseEntity(mapOf("mensaje" to "La contraseña debe tener entre 5 y 20 caracteres"), HttpStatus.BAD_REQUEST)
        }

        usuario.password = passwordEncoder.encode(password)
        usuarioRepository.save(usuario)

        return ResponseEntity(
            mapOf("mensaje" to "Usuario registrado exitosamente", "usuario" to usuario),
            HttpStatus.CREATED
        )
    }

    fun deleteUsuario(id: Long): Usuario {
        val usuario = usuarioRepository.findById(id)
            .orElseThrow { NotFoundException("Usuario con id $id no encontrada") }

        usuarioRepository.delete(usuario)
        return usuario
    }
}
