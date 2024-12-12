package com.example.API_Organizador_Eventos.service

import com.example.API_Organizador_Eventos.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository



}