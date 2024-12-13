package com.example.API_Organizador_Eventos.controller

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
    fun crearActividad(@RequestBody Actividad: Actividad) : ResponseEntity<Any>? {

        try {
            actividadService.agregarActividad(Actividad)
            return ResponseEntity(Actividad, HttpStatus.CREATED)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo en la creacion de la actividad"), HttpStatus.UNAUTHORIZED)
        }


    }

    @DeleteMapping("/deleteActividad")
    fun deleteActividad(@RequestBody id: Long) : ResponseEntity<Any>? {

        try {
            val actividad=actividadService.deleteActividad(id)
            return ResponseEntity(actividad, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo eliminando la actividad"), HttpStatus.UNAUTHORIZED)
        }


    }

    @GetMapping("/getActividad")
    fun getActividad(@RequestBody id: Long) : ResponseEntity<Any>? {

        try {
            val actividad=actividadService.getActividad(id)
            return ResponseEntity(actividad, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo buscando la actividad"), HttpStatus.UNAUTHORIZED)
        }


    }

    @PutMapping("/updateActividad")
    fun updateActividad(@RequestBody actividad: Actividad) : ResponseEntity<Any>? {
        try {
            val act=actividadService.updateActividad(actividad)
            return ResponseEntity(act, HttpStatus.OK)

        }catch (e:Exception){
            return ResponseEntity(mapOf("mensaje" to "Fallo actualizando la actividad"), HttpStatus.UNAUTHORIZED)
        }

    }

}
