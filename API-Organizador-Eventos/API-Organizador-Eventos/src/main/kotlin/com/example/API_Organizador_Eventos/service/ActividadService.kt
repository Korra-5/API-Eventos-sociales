package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class ActividadService {

    @Autowired
    private lateinit var actividadRepository: ActividadRepository

    fun agregarActividad(actividad: Actividad) {
        actividadRepository.save(actividad)
    }

    fun deleteActividad(id: Long) {
        val actividadOptional: Optional<Actividad> = actividadRepository.findActividadById(id)

        if (actividadOptional.isPresent) {
            val actividad = actividadOptional.get()
            actividadRepository.delete(actividad)
        } else {
            throw NoSuchElementException("Actividad con id $id no encontrada")
        }
    }

    fun getActividad(id: Long):Actividad {
        val actividadOptional: Optional<Actividad> = actividadRepository.findActividadById(id)

        if (actividadOptional.isPresent) {

            return actividadOptional.get()
        } else {
            throw NoSuchElementException("Actividad con id $id no encontrada")
        }
    }

    fun updateActividad(actividad: Actividad): Actividad {
        val actividadExistente = actividadRepository.findById(actividad.id ?: throw IllegalArgumentException("ID no puede ser nulo"))
            .orElseThrow { EntityNotFoundException("Actividad con id ${actividad.id} no encontrada") }

        actividadExistente.apply {
            nombre = actividad.nombre
            descripcion = actividad.descripcion
            fecha = actividad.fecha
            lugar = actividad.lugar
            organizador = actividad.organizador
            comunidad = actividad.comunidad
            participantes = actividad.participantes
        }

        return actividadRepository.save(actividadExistente)
    }


}