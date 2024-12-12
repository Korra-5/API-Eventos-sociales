package com.example.API_Organizador_Eventos.controller

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Usuario
import com.example.API_Organizador_Eventos.service.ActividadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/actividad")
class ActividadController {


    @Autowired
    private lateinit var actividadService: ActividadService



    @PostMapping("/crearActividad")
    fun crearActividad(@RequestBody Actividad: Actividad) : ResponseEntity<Any>? {

        try {
            actividadService.agregarActividad(Actividad)
            return ResponseEntity(null, HttpStatus.CREATED)

        }catch (e:Exception){
            throw e
        }


    }

}
