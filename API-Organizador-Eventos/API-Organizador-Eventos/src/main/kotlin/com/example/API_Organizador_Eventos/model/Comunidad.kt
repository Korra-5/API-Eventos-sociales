package com.example.API_Organizador_Eventos.model

import jakarta.persistence.*
import java.util.*
@Entity
@Table(name = "comunidades")
data class Comunidad(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nombre: String? = null,

    var descripcion: String? = null,

    @ManyToOne
    @JoinColumn(name = "creador_id", nullable = false)
    var creador: Usuario? = null,

    @Column(name = "fecha_creacion", nullable = false)
    var fechaCreacion: Date = Date(),

    @OneToMany(mappedBy = "comunidad", cascade = [CascadeType.ALL], orphanRemoval = true)
    var actividades: List<Actividad> = mutableListOf()
)
