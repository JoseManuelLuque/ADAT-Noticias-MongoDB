package Noticias.model

import java.util.*

data class Comentario(
    val autor: Usuario,
    val noticia: Noticia,
    val texto: String,
    val fecha: Date
)