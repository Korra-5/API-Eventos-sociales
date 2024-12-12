package com.example.API_Organizador_Eventos.repository

import com.example.API_Organizador_Eventos.model.Actividad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActividadRepository : JpaRepository<Actividad, Long> {

}