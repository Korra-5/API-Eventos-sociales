package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.error.exception.BadRequestException
import com.example.API_Organizador_Eventos.error.exception.NotFoundException
import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Comunidad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import com.example.API_Organizador_Eventos.repository.UsuarioRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException
@Service
class ComunidadService {

    @Autowired
    private lateinit var comunidadRepository: ComunidadRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var actividadRepository: ActividadRepository

    fun agregarComunidad(comunidad: Comunidad) {
        validarComunidad(comunidad)
        comunidadRepository.save(comunidad)
    }

    fun deleteComunidad(id: Long) {
        val comunidad = comunidadRepository.findById(id)
            .orElseThrow { NoSuchElementException("Comunidad con id $id no encontrada") }
        comunidadRepository.delete(comunidad)
    }

    fun getComunidad(id: Long): Comunidad {
        return comunidadRepository.findById(id)
            .orElseThrow { NoSuchElementException("Comunidad con id $id no encontrada") }
    }

    fun updateComunidad(comunidad: Comunidad): Comunidad {
        val comunidadExistente = comunidadRepository.findById(comunidad.id ?: throw IllegalArgumentException("ID no puede ser nulo"))
            .orElseThrow { EntityNotFoundException("Comunidad con id ${comunidad.id} no encontrada") }

        validarComunidad(comunidad)

        comunidadExistente.apply {
            nombre = comunidad.nombre
            descripcion = comunidad.descripcion
            creador = comunidad.creador
            fechaCreacion = comunidad.fechaCreacion
            actividades = comunidad.actividades
        }

        return comunidadRepository.save(comunidadExistente)
    }

    private fun validarComunidad(comunidad: Comunidad) {
        if (comunidad.nombre.isNullOrBlank() || comunidad.nombre!!.length > 30) {
            throw BadRequestException("El nombre de la comunidad no puede ser nulo ni superar los 30 caracteres")
        }

        if (comunidad.descripcion != null && comunidad.descripcion!!.length > 100) {
            throw BadRequestException("La descripción no puede superar los 100 caracteres")
        }

        val creadorId = comunidad.creador?.id
            ?: throw BadRequestException("La comunidad debe tener un creador con un ID válido")
        if (!usuarioRepository.existsById(creadorId)) {
            throw NotFoundException("Creador con id $creadorId no encontrado")
        }

        val actividadIds = comunidad.actividades.mapNotNull { it.id }
        if (actividadIds.isNotEmpty() && !actividadRepository.findAllById(actividadIds).map { it.id }.containsAll(actividadIds)) {
            throw NotFoundException("Una o más actividades no existen en el sistema")
        }

        if (comunidad.fechaCreacion == null) {
            throw BadRequestException("La fecha de creación no puede ser nula")
        }
    }
}
