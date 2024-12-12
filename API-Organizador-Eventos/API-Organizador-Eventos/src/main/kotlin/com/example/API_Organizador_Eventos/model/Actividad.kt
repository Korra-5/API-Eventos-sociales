package com.example.API_Organizador_Eventos.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "actividades")
data class Actividad(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "comunidad_id", nullable = false)
    var comunidad: Comunidad? = null,

    @Column(nullable = false)
    var nombre: String? = null,

    var descripcion: String? = null,

    @Column(nullable = false)
    var fecha: Date = Date(),

    @Column(nullable = false)
    var lugar: String? = null,

    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    var organizador: Usuario? = null,

    @ManyToMany
    @JoinTable(
        name = "participantes_actividad",
        joinColumns = [JoinColumn(name = "actividad_id")],
        inverseJoinColumns = [JoinColumn(name = "usuario_id")]
    )
    var participantes: Set<Usuario> = mutableSetOf()
)
