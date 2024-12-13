package com.example.API_Organizador_Eventos.repository

import com.example.API_Organizador_Eventos.model.Comunidad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComunidadRepository : JpaRepository<Comunidad, Long> {
}