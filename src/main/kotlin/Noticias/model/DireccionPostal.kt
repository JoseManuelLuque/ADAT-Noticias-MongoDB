package Noticias.model

data class DireccionPostal(
    val calle: String,
    val numero: String,
    val puerta: String,
    val codigoPostal: String,
    val ciudad: String
)