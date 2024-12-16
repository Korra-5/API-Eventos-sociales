# Social.Me

## Idea del Proyecto
**Social.Me** es una aplicación diseñada para facilitar la creación y gestión de eventos sociales. A través de esta plataforma, los usuarios pueden formar comunidades y organizar actividades dentro de estas, fomentando la interacción social y la colaboración en torno a intereses comunes.

La idea es proporcionar un espacio digital donde personas de diferentes edades y contextos puedan conectar, crear eventos y participar activamente en actividades de sus comunidades.

## Justificación del Proyecto
En la actualidad, existen pocas aplicaciones que combinen de manera eficiente la creación de comunidades con la gestión de actividades sociales. Social.Me busca llenar este vacío, ofreciendo una plataforma sencilla e intuitiva, pero con un enfoque innovador:

## Tablas que Intervendrán en el Proyecto
El sistema se basa en tres tablas principales: **Usuario**, **Comunidad** y **Actividad**. Estas tablas están relacionadas para garantizar la funcionalidad de la aplicación.

### Tabla `Usuario`
**Descripción:** Representa a los usuarios de la aplicación.
- **Atributos:**
  - `id`: Identificador único del usuario.
  - `username`: Nombre de usuario único.
  - `roles`: Roles asignados al usuario (por defecto: "USER").
  - `password`: Contraseña del usuario.
- **Relaciones:**
  - **Uno a Muchos** con `Comunidad`: Un usuario puede ser creador de varias comunidades.
  - **Muchos a Muchos** con `Actividad`: Un usuario puede participar en varias actividades.

### Tabla `Comunidad`
**Descripción:** Representa comunidades creadas por los usuarios.
- **Atributos:**
  - `id`: Identificador único de la comunidad.
  - `nombre`: Nombre de la comunidad.
  - `descripcion`: Descripción de la comunidad.
  - `fechaCreacion`: Fecha de creación de la comunidad.
- **Relaciones:**
  - **Muchos a Uno** con `Usuario`: Una comunidad tiene un creador.
  - **Uno a Muchos** con `Actividad`: Una comunidad puede tener múltiples actividades asociadas.

### Tabla `Actividad`
**Descripción:** Representa las actividades que se realizan dentro de una comunidad.
- **Atributos:**
  - `id`: Identificador único de la actividad.
  - `nombre`: Nombre de la actividad.
  - `descripcion`: Descripción de la actividad.
  - `fecha`: Fecha en la que se llevará a cabo la actividad.
  - `lugar`: Lugar donde se realizará la actividad.
- **Relaciones:**
  - **Muchos a Uno** con `Comunidad`: Una actividad pertenece a una comunidad.
  - **Muchos a Uno** con `Usuario`: Una actividad tiene un organizador.
  - **Muchos a Muchos** con `Usuario`: Una actividad tiene múltiples participantes.
