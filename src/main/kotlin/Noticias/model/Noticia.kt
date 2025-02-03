package Noticias.model

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    val autor: Usuario,
    val tags: List<String>?
)