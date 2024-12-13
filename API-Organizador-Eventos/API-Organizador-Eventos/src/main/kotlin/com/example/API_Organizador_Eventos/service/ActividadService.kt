package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
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
}