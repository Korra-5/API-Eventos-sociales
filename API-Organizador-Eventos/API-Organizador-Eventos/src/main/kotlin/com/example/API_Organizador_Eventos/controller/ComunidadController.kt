package com.example.API_Organizador_Eventos.controller

import com.example.API_Organizador_Eventos.error.exception.BadRequestException
import com.example.API_Organizador_Eventos.error.exception.NotFoundException
import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Comunidad
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import com.example.API_Organizador_Eventos.service.ComunidadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comunidad")
class ComunidadController {

    @Autowired
    private lateinit var comunidadService: ComunidadService

    @PostMapping("/crearComunidad")
    fun crearComunidad(@RequestBody comunidad: Comunidad): ResponseEntity<Any> {
        return try {
            comunidadService.agregarComunidad(comunidad)
            ResponseEntity(comunidad, HttpStatus.CREATED)
        } catch (e: BadRequestException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/deleteComunidad")
    fun deleteComunidad(@RequestBody id: Long): ResponseEntity<Any> {
        return try {
            comunidadService.deleteComunidad(id)
            ResponseEntity.ok(mapOf("mensaje" to "Comunidad eliminada correctamente"))
        } catch (e: NoSuchElementException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/getComunidad")
    fun getComunidad(@RequestBody id: Long): ResponseEntity<Any> {
        return try {
            val comunidad = comunidadService.getComunidad(id)
            ResponseEntity.ok(comunidad)
        } catch (e: NoSuchElementException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/updateComunidad")
    fun updateComunidad(@RequestBody comunidad: Comunidad): ResponseEntity<Any> {
        return try {
            val updatedComunidad = comunidadService.updateComunidad(comunidad)
            ResponseEntity.ok(updatedComunidad)
        } catch (e: BadRequestException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        }
    }
}
