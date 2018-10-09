# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

[![Build Status](https://travis-ci.org/miw-upm/APAW-themes-layers.svg?branch=develop)](https://travis-ci.org/miw-upm/APAW-themes-layers)

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
- `sede`: String
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
##### Autora: Vanesa Paniego Seco.
