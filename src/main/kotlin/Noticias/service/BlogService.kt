package Noticias.service

import Noticias.logs.LogsControl
import Noticias.model.Comentario
import Noticias.model.Estado
import Noticias.model.Noticia
import Noticias.model.Usuario
import Noticias.utils.ConexionMongo
import com.mongodb.client.model.IndexOptions
import org.bson.Document

class BlogService {

    // Crear una instancia de la base de datos y las colecciones
    private val db = ConexionMongo.getDatabase("Noticias")
    private val usuarios = db.getCollection("Usuarios", Usuario::class.java)
    private val noticias = db.getCollection("Noticias", Noticia::class.java)
    private val comentarios = db.getCollection("Comentarios", Comentario::class.java)

    init {
        crearIndices()
    }

    // Crear índices para las colecciones, para cumplir la unicidad de los campos de la lógica de negocio
    private fun crearIndices() {
        usuarios.createIndex(Document("nombreUsuario", 1), IndexOptions().unique(true))
        noticias.createIndex(Document("fechaPublicacion", 1), IndexOptions().unique(true))
    }

    fun registrarUsuario(usuario: Usuario) {
        LogsControl.info("Registrando usuario: ${usuario._id}")
        usuarios.insertOne(usuario)
    }

    fun publicarNoticia(noticia: Noticia) {
        LogsControl.info("Publicando noticia: ${noticia.titulo}")
        noticias.insertOne(noticia)
    }

    fun listarNoticiasPorUsuario(usuario: Usuario): List<Noticia> {
        LogsControl.info("Listando noticias por usuario: ${usuario._id}")
        return noticias.find(Document("autor._id", usuario._id)).toList()
    }

    fun listarComentariosPorNoticia(noticia: Noticia): List<Comentario> {
        LogsControl.info("Listando comentarios por noticia: ${noticia._id}")
        return comentarios.find(Document("noticia._id", noticia._id)).toList()
    }

    fun buscarNoticiasPorEtiquetas(etiquetas: List<String>): List<Noticia> {
        LogsControl.info("Buscando noticias por etiquetas: $etiquetas")
        return noticias.find(Document("tags", Document("\$in", etiquetas))).toList()
    }

    fun listarUltimasNoticias(limit: Int = 10): List<Noticia> {
        LogsControl.info("Listando las últimas $limit noticias")
        return noticias.find().sort(Document("fechaPublicacion", -1)).limit(limit).toList()
    }

    fun escribirComentario(comentario: Comentario) {
        val usuario = comentario.autor
        if (Estado.INACTIVO in usuario.estado || Estado.BANEADO in usuario.estado) {
            LogsControl.warning("El usuario ${usuario._id} no puede escribir comentarios.")
            throw IllegalStateException("El usuario no puede escribir comentarios.")
        }
        LogsControl.info("Escribiendo comentario por usuario: ${usuario._id}")
        comentarios.insertOne(comentario)
    }

    fun listarUsuarios(): List<Usuario> {
        LogsControl.info("Listando todos los usuarios")
        return usuarios.find().toList()
    }

    fun listarNoticias(): List<Noticia> {
        LogsControl.info("Listando todas las noticias")
        return noticias.find().toList()
    }
}