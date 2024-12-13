package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.model.Actividad
import com.example.API_Organizador_Eventos.model.Comunidad
import com.example.API_Organizador_Eventos.repository.ActividadRepository
import com.example.API_Organizador_Eventos.repository.ComunidadRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ComunidadService {

    @Autowired
    private lateinit var comunidadRepository: ComunidadRepository

    fun agregarComunidad(Comunidad: Comunidad) {
        comunidadRepository.save(Comunidad)
    }
}