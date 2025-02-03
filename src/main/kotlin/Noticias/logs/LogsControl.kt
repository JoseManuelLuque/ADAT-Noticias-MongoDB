package Noticias.logs

import java.io.IOException
import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

// Objeto para controlar los logs
object LogsControl {
    // Crear una instancia del logger
    private val logger: Logger = Logger.getLogger(LogsControl::class.java.name)

    init {
        try {
            // Configurar el FileHandler para escribir en un archivo
            val fileHandler = FileHandler("Pruebas.log", true)
            fileHandler.formatter = SimpleFormatter()
            logger.addHandler(fileHandler)
        } catch (e: IOException) {
            // Registrar un mensaje de error si hay problemas al configurar el FileHandler
            logger.severe("Error al configurar el FileHandler: ${e.message}")
        }
    }

    // Método para registrar mensajes de información
    fun info(message: String) {
        logger.info(message)
    }

    // Método para registrar mensajes de advertencia
    fun warning(message: String) {
        logger.warning(message)
    }
}