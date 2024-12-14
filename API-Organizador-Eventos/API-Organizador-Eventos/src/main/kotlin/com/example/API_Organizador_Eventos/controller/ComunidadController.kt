package com.example.API_Organizador_Eventos.controller

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
    fun crearComunidad(@RequestBody Comunidad: Comunidad): ResponseEntity<Any>? {

        try {
            comunidadService.agregarComunidad(Comunidad)
            return ResponseEntity(Comunidad, HttpStatus.CREATED)

        } catch (e: Exception) {
            return ResponseEntity(mapOf("mensaje" to "Fallo en la creacion"), HttpStatus.UNAUTHORIZED)

        }

    }

    @DeleteMapping("/deleteComunidad")
    fun deleteActividad(@RequestBody id: Long) : ResponseEntity<Any>? {

        try {
            val actividad=comunidadService.deleteComunidad(id)
            return ResponseEntity(actividad, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo eliminando la comunidad"), HttpStatus.UNAUTHORIZED)
        }


    }

    @GetMapping("/getComunidad")
    fun getActividad(@RequestBody id: Long) : ResponseEntity<Any>? {

        try {
            val actividad=comunidadService.getComunidad(id)
            return ResponseEntity(actividad, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo buscando la comunidad"), HttpStatus.UNAUTHORIZED)
        }


    }

    @PutMapping("/updateComunidad")
    fun updateActividad(@RequestBody comunidad: Comunidad) : ResponseEntity<Any>? {
        try {
            val com=comunidadService.updateComunidad(comunidad)
            return ResponseEntity(com, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo actualizando la comunidad"), HttpStatus.UNAUTHORIZED)
        }

    }
}