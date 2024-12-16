package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.error.exception.BadRequestException
import com.example.API_Organizador_Eventos.error.exception.NotFoundException
import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import com.example.API_Organizador_Eventos.repository.UsuarioRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class ActividadService {

    @Autowired
    private lateinit var actividadRepository: ActividadRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var comunidadRepository: ComunidadRepository



    fun agregarActividad(actividad: Actividad) {
        validarActividad(actividad)
        verificarExistenciaDeIds(actividad)
        actividadRepository.save(actividad)
    }

    fun deleteActividad(id: Long) {
        val actividad = actividadRepository.findById(id)
            .orElseThrow { NotFoundException("Actividad con id $id no encontrada") }

        actividadRepository.delete(actividad)
    }

    fun getActividad(id: Long): Actividad {
        return actividadRepository.findById(id)
            .orElseThrow { NotFoundException("Actividad con id $id no encontrada") }
    }

    fun updateActividad(actividad: Actividad): Actividad {
        validarActividad(actividad)
        verificarExistenciaDeIds(actividad)

        val actividadExistente = actividadRepository.findById(
            actividad.id
                ?: throw BadRequestException("ID de actividad no puede ser nulo")
        )
            .orElseThrow { NotFoundException("Actividad con id ${actividad.id} no encontrada") }

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

    private fun validarActividad(actividad: Actividad) {
        if (actividad.nombre.isNullOrBlank() || actividad.nombre.toString().length > 30) {
            throw BadRequestException("El nombre de la actividad no puede ser nulo o exceder 30 caracteres")
        }
        if (!actividad.descripcion.isNullOrBlank() && actividad.descripcion.toString().length > 100) {
            throw BadRequestException("La descripción no puede exceder 100 caracteres")
        }
        if (actividad.lugar.isNullOrBlank() || actividad.lugar.toString().length > 30) {
            throw BadRequestException("El lugar no puede ser nulo o exceder 30 caracteres")
        }
    }

    private fun verificarExistenciaDeIds(actividad: Actividad) {
        val organizadorId = actividad.organizador?.id
            ?: throw BadRequestException("El organizador debe tener un ID válido")
        if (!usuarioRepository.existsById(organizadorId)) {
            throw NotFoundException("Organizador con id $organizadorId no encontrado")
        }

        val participanteIds = actividad.participantes.mapNotNull { it.id }
        if (participanteIds.isNotEmpty() && !usuarioRepository.existsByIdIn(participanteIds)) {
            throw NotFoundException("Uno o más participantes no existen en el sistema")
        }

        val comunidadId = actividad.comunidad?.id
            ?: throw BadRequestException("La comunidad debe tener un ID válido")
        if (!comunidadRepository.existsById(comunidadId)) {
            throw NotFoundException("Comunidad con id $comunidadId no encontrada")
        }
    }

}

