# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

[![Build Status](https://travis-ci.org/vpaniego/APAW-ECP2-VanesaPaniego.svg?branch=develop)](https://travis-ci.org/vpaniego/APAW-ECP2-VanesaPaniego)

## Tecnologías necesarias
* Java
* Maven
* GitHub

## Diseño de entidades
![album-class-diagram](https://github.com/vpaniego/APAW-ECP2-VanesaPaniego/blob/develop/docs/APAW-ECP2-DiagramaClases-Album.png)

## Arquitectura
![album-entities-class-diagram](https://github.com/vpaniego/APAW-ECP2-VanesaPaniego/blob/develop/docs/APAW-ECP2-ArquitecturaClases-Album.png)

### Responsabilidades
#### Dispatcher
* Centraliza las peticiones y hace de repartidor
* Recupera los datos de la petición y los pasa como parámetros de método
* Captura las excepciones y las convierte en errores Http
#### apiControllers
* Define el path del recurso
* Valida la entrada
* Traspasa la petición a los controladores de la capa de negocio
#### businessControllers
* Procesa la petición, apoyándose en los DAO’s
* Crea las entidades a partir de los DTO’s
* Gestiona la respuesta a partir de las entidades. Delega en los DTO’s la creación a partir de la entidad
#### daos
* Gestionan la BD
#### entities
* Son las entidades persistentes en la BD

## API
### POST /sellos
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `sede`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /sellos/{id}
#### Parámetros del cuerpo
- `id`: String (**requerido**)
- `sede`: String
#### Respuesta
- 200 OK
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /albumes
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `artista`: String (**requerido**)
- `fechaEdicion`: LocalDateTime (**requerido**)
- `numPistas`: Integer (**requerido**)
- `genero`: Genero (**requerido**)
- `selloId`: String (**requerido**)
#### Respuesta
- 200 OK
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /sellos
#### Respuesta
- 200 OK
  - `[ {id:String,nombre:String} ]`
---
### PATCH /albumes/{id}/genero
#### Parámetros del cuerpo
- `genero`: String (**requerido**)
#### Respuesta
- 200 OK
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /programas-radio
#### Parámetros del cuerpo
- `nombre`: String (**requerido**)
- `nocturno`: Boolean (**requerido**)
- `diaEmision`: String (**requerido**)
- `albumId`: String (**requerido**)
#### Respuesta
- 200 OK
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /albumes/search?q=numPistas:>=7
#### Respuesta
- 200 OK
  - `[ {id:String,nombre:String,artista:String,numPistas:Integer} ]`
- 403 BAD_REQUEST
---
### DELETE /sellos/{id}
#### Respuesta
- 200 OK
---

##### Autora: Vanesa Paniego Seco.
