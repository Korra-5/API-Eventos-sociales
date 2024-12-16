package com.example.API_Organizador_Eventos.controller

import com.example.API_Organizador_Eventos.error.exception.BadRequestException
import com.example.API_Organizador_Eventos.error.exception.NotFoundException
import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Usuario
import com.example.API_Organizador_Eventos.service.ActividadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/actividad")
class ActividadController {

    @Autowired
    private lateinit var actividadService: ActividadService

    @PostMapping("/crearActividad")
    fun crearActividad(@RequestBody actividad: Actividad): ResponseEntity<Any> {
        return try {
            actividadService.agregarActividad(actividad)
            ResponseEntity(actividad, HttpStatus.CREATED)
        } catch (e: BadRequestException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/deleteActividad")
    fun deleteActividad(@RequestBody id: Long): ResponseEntity<Any> {
        return try {
            actividadService.deleteActividad(id)
            ResponseEntity(mapOf("mensaje" to "Actividad eliminada correctamente"), HttpStatus.OK)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/getActividad")
    fun getActividad(@RequestBody id: Long): ResponseEntity<Any> {
        return try {
            val actividad = actividadService.getActividad(id)
            ResponseEntity(actividad, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/updateActividad")
    fun updateActividad(@RequestBody actividad: Actividad): ResponseEntity<Any> {
        return try {
            val act = actividadService.updateActividad(actividad)
            ResponseEntity(act, HttpStatus.OK)
        } catch (e: BadRequestException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        } catch (e: NotFoundException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
