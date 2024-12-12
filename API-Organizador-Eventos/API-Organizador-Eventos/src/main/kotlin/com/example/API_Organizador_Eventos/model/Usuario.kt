package com.example.API_Organizador_Eventos.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    var roles: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @OneToMany(mappedBy = "creador", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comunidades: List<Comunidad> = mutableListOf(),

    @ManyToMany(mappedBy = "participantes")
    var actividades: Set<Actividad> = mutableSetOf()
)
