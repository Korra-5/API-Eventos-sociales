package com.example.API_Organizador_Eventos.repository

import com.example.API_Organizador_Eventos.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long>{
    fun findByUsername(username:String):List<Usuario>
}