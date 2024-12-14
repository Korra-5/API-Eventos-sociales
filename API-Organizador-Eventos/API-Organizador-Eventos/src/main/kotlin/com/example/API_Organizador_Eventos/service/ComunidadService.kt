package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Comunidad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class ComunidadService {

    @Autowired
    private lateinit var comunidadRepository: ComunidadRepository

    fun agregarComunidad(Comunidad: Comunidad) {
        comunidadRepository.save(Comunidad)
    }
    fun deleteComunidad(id: Long) {
        val comunidadOptional: Optional<Comunidad> = comunidadRepository.findComunidadById(id)

        if (comunidadOptional.isPresent) {
            val comunidad = comunidadOptional.get()
            comunidadRepository.delete(comunidad)
        } else {
            throw NoSuchElementException("Comunidad con id $id no encontrada")
        }
    }

    fun getComunidad(id: Long):Comunidad {
        val comunidadOptional: Optional<Comunidad> = comunidadRepository.findComunidadById(id)

        if (comunidadOptional.isPresent) {

            return comunidadOptional.get()
        } else {
            throw NoSuchElementException("Comunidad con id $id no encontrada")
        }
    }

    fun updateComunidad(comunidad: Comunidad):Comunidad {
        val comunidadExists = comunidadRepository.findComunidadById(comunidad.id ?: throw IllegalArgumentException("ID no puede ser nulo"))
            .orElseThrow { EntityNotFoundException("Comunidad con id ${comunidad.id} no encontrada") }

        comunidadExists.apply {

        }

        return comunidadRepository.save(comunidadExists)
    }


}
