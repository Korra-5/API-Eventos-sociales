package com.example.API_Organizador_Eventos.controller

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Comunidad
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import com.example.API_Organizador_Eventos.service.ComunidadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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
}