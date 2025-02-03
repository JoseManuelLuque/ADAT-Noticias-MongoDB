package Noticias.model

import java.time.Instant

data class Comentario(
    val autor: Usuario,
    val noticia: Noticia,
    val texto: String,
    val fecha: Instant
)