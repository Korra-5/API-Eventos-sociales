package com.example.API_Organizador_Eventos.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    var roles: String? = "USER",

    @Column(nullable = false)
    var password: String? = null,

    @OneToMany(mappedBy = "creador")
    @JsonIgnore
    var comunidades: List<Comunidad> = mutableListOf(),

    @ManyToMany(mappedBy = "participantes")
    @JsonIgnore
    var actividades: List<Actividad> = mutableListOf()
)