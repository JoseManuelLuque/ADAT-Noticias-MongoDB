package Noticias.model

import org.bson.codecs.pojo.annotations.BsonId

data class Usuario(
    @BsonId
    val _id: String, // Email del usuario
    val nombreCompleto: String,
    val nombreUsuario: String,
    val estado: Set<Estado>, // Activo, Inactivo, banneado, no banneado
    val direccionPostal: DireccionPostal,
    val telefonos: List<String>
)