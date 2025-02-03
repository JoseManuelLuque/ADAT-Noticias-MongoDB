package Noticias.model

import org.bson.codecs.pojo.annotations.BsonId
import java.time.Instant

data class Noticia(
    @BsonId
    val _id: String,
    val titulo: String,
    val cuerpo: String,
    val autor: Usuario,
    val tags: List<String>?,
    val fechaPublicacion: Instant = Instant.now()

)