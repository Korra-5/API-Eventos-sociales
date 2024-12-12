package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActividadService {

    @Autowired
    private lateinit var actividadRepository: ActividadRepository

    fun agregarActividad(actividad: Actividad) {
        actividadRepository.save(actividad)
    }
}