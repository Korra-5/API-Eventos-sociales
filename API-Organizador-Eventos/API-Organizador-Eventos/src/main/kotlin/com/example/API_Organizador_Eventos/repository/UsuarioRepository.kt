package com.example.API_Organizador_Eventos.repository

import com.example.API_Organizador_Eventos.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByUsername(username: String): Optional<Usuario>
    fun findUsuariosById(id:Long):Optional <Usuario>
    fun existsByIdIn(ids: List<Long>): Boolean
    fun existsByUsername(username: String): Boolean

}
