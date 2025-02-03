package Noticias

import Noticias.model.*
import Noticias.service.BlogService
import org.bson.types.ObjectId
import java.time.Instant

fun main() {
    val blogService = BlogService()

    while (true) {
        // Imprimir el menú de opciones
        imprimirMenu()
        val opcion = readln().toInt()

        // Ejecutar la opción seleccionada
        when (opcion) {
            1 -> registrarUsuario(blogService) // Registrar un nuevo usuario
            2 -> publicarNoticia(blogService) // Publicar una nueva noticia
            3 -> escribirComentario(blogService) // Escribir un comentario en una noticia
            4 -> listarNoticiasPorUsuario(blogService) // Listar noticias por usuario
            5 -> listarComentariosPorNoticia(blogService) // Listar comentarios por noticia
            6 -> buscarNoticiasPorEtiquetas(blogService) // Buscar noticias por etiquetas
            7 -> listarUltimasNoticias(blogService) // Listar las últimas 10 noticias
            8 -> {
                println("Saliendo...") // Salir del programa
                break
            }

            else -> println("Opción no válida.")
        }
    }
}

fun imprimirMenu() {
    // Imprimir las opciones del menú
    println("Seleccione una opción:")
    println("1. Registrar usuario")
    println("2. Publicar noticia")
    println("3. Escribir comentario")
    println("4. Listar noticias por usuario")
    println("5. Listar comentarios por noticia")
    println("6. Buscar noticias por etiquetas")
    println("7. Listar las 10 últimas noticias")
    println("8. Salir")
}

fun registrarUsuario(blogService: BlogService) {
    // Solicitar y leer los datos del nuevo usuario
    println("Ingrese el email del usuario:")
    val email = readln()
    println("Ingrese el nombre completo:")
    val nombreCompleto = readln()
    println("Ingrese el nombre de usuario:")
    val nombreUsuario = readln()
    println("Ingrese la calle:")
    val calle = readln()
    println("Ingrese el número:")
    val numero = readln()
    println("Ingrese la puerta:")
    val puerta = readln()
    val codigoPostal = introducirCodigoPostal()
    println("Ingrese la ciudad:")
    val ciudad = readln()
    println("Ingrese el teléfono:")
    val telefono = readln()

    // Crear la dirección postal y el usuario
    val direccion = DireccionPostal(calle, numero, puerta, codigoPostal, ciudad)
    val usuario = Usuario(
        _id = email,
        nombreCompleto = nombreCompleto,
        nombreUsuario = nombreUsuario,
        estado = setOf(Estado.ACTIVO),
        direccionPostal = direccion,
        telefonos = listOf(telefono)
    )
    // Registrar el usuario en el servicio
    blogService.registrarUsuario(usuario)
    println("Usuario registrado con éxito.")
}

fun introducirCodigoPostal(): String {
    // Solicitar y leer el código postal
    println("Ingrese el código postal:")
    return readln()
}

fun publicarNoticia(blogService: BlogService) {
    // Solicitar y leer los datos de la noticia
    println("Ingrese el email del autor:")
    val email = readln()
    val usuario = blogService.listarUsuarios().find { it._id == email }
    if (usuario != null) {
        println("Ingrese el título de la noticia:")
        val titulo = readln()
        println("Ingrese el cuerpo de la noticia:")
        val cuerpo = readln()
        println("Ingrese las etiquetas (separadas por comas):")
        val tags = readln().split(",")

        // Crear y publicar la noticia
        val noticia = Noticia(
            _id = ObjectId().toString(),
            titulo = titulo,
            cuerpo = cuerpo,
            autor = usuario,
            tags = tags,
            fechaPublicacion = Instant.now()
        )
        blogService.publicarNoticia(noticia)
        println("Noticia publicada con éxito.")
    } else {
        println("Usuario no encontrado.")
    }
}

fun escribirComentario(blogService: BlogService) {
    // Solicitar y leer los datos del comentario
    println("Ingrese el email del autor del comentario:")
    val email = readln()
    val usuario = blogService.listarUsuarios().find { it._id == email }
    if (usuario != null) {
        println("Ingrese el ID de la noticia:")
        val noticiaId = readln()
        val noticia = blogService.listarNoticias().find { it._id == noticiaId }
        if (noticia != null) {
            println("Ingrese el texto del comentario:")
            val texto = readln()

            // Crear y publicar el comentario
            val comentario = Comentario(
                autor = usuario,
                noticia = noticia,
                texto = texto,
                fecha = Instant.now()
            )
            blogService.escribirComentario(comentario)
            println("Comentario publicado con éxito.")
        } else {
            println("Noticia no encontrada.")
        }
    } else {
        println("Usuario no encontrado.")
    }
}

fun listarNoticiasPorUsuario(blogService: BlogService) {
    // Solicitar y leer el email del usuario
    println("Ingrese el email del usuario:")
    val email = readln()
    val usuario = blogService.listarUsuarios().find { it._id == email }
    if (usuario != null) {
        // Listar y mostrar las noticias del usuario
        val noticias = blogService.listarNoticiasPorUsuario(usuario)
        println("Noticias por usuario: $noticias")
    } else {
        println("Usuario no encontrado.")
    }
}

fun listarComentariosPorNoticia(blogService: BlogService) {
    // Solicitar y leer el ID de la noticia
    println("Ingrese el ID de la noticia:")
    val noticiaId = readln()
    val noticia = blogService.listarNoticias().find { it._id == noticiaId }
    if (noticia != null) {
        // Listar y mostrar los comentarios de la noticia
        val comentarios = blogService.listarComentariosPorNoticia(noticia)
        println("Comentarios por noticia: $comentarios")
    } else {
        println("Noticia no encontrada.")
    }
}

fun buscarNoticiasPorEtiquetas(blogService: BlogService) {
    // Solicitar y leer las etiquetas
    println("Ingrese las etiquetas (separadas por comas):")
    val etiquetas = readln().split(",")
    // Buscar y mostrar las noticias por etiquetas
    val noticias = blogService.buscarNoticiasPorEtiquetas(etiquetas)
    println("Noticias por etiquetas: $noticias")
}

fun listarUltimasNoticias(blogService: BlogService) {
    // Listar y mostrar las últimas noticias
    val ultimasNoticias = blogService.listarUltimasNoticias()
    println("Últimas noticias: $ultimasNoticias")
}