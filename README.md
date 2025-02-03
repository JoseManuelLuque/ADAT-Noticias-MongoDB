# ADAT-Noticias-MongoDB

Este proyecto es un sistema de gestión de noticias desarrollado con Kotlin y Java. Permite a los usuarios registrarse, publicar artículos de noticias y escribir comentarios. El sistema asegura que los correos electrónicos y nombres de usuario sean únicos, las fechas de publicación de los artículos sean únicas e inmutables, y los comentarios sean inmutables una vez publicados.

## Funcionalidades

- **Registro de usuarios**: Los usuarios pueden registrarse proporcionando un correo electrónico, nombre completo, nombre de usuario, dirección y número de teléfono.
- **Publicación de noticias**: Los usuarios pueden publicar artículos de noticias con un título, cuerpo y etiquetas.
- **Escritura de comentarios**: Los usuarios pueden escribir comentarios en los artículos de noticias. Los usuarios con estado `INACTIVO` o `BANEADO` no pueden escribir comentarios.
- **Listar noticias y comentarios**: Se pueden listar las noticias por usuario, los comentarios por noticia, buscar noticias por etiquetas y listar las últimas noticias.

## Uso

### Registro de Usuario

Para registrar un nuevo usuario, se deben proporcionar los detalles requeridos como correo electrónico, nombre completo, nombre de usuario, dirección y número de teléfono.

### Publicación de Noticias

Para publicar un artículo de noticias, se deben proporcionar el correo electrónico del autor, título, cuerpo y etiquetas.

### Escritura de Comentarios

Para escribir un comentario, se deben proporcionar el correo electrónico del autor, el ID del artículo de noticias y el texto del comentario. Los usuarios con estado `INACTIVO` o `BANEADO` no pueden escribir comentarios.

### Listar Noticias y Comentarios

Se pueden listar las noticias por usuario, los comentarios por noticia, buscar noticias por etiquetas y listar las últimas noticias.
