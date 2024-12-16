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
 
  
## Endpoints

### **Usuarios**

1. **POST /usuarios/signup**
   - **Descripción:** Registra un nuevo usuario en la aplicación.

2. **POST /usuarios/login**
   - **Descripción:** Permite a un usuario autenticarse en la aplicación y obtener un token JWT.

3. **DELETE /usuarios/deleteUsuario**
   - **Descripción:** Elimina la cuenta de un usuario. Solo el administrador o el propio usuario pueden eliminar su cuenta.

### **Comunidades**

1. **POST /comunidad/crearComunidad**
   - **Descripción:** Crea una nueva comunidad.

2. **GET /comunidad/getComunidad**
   - **Descripción:** Obtiene los detalles de una comunidad por su `id`.

3. **PUT /comunidad/updateComunidad**
   - **Descripción:** Actualiza la información de una comunidad.

4. **DELETE /comunidad/deleteComunidad**
   - **Descripción:** Elimina una comunidad existente.

### **Actividades**

1. **POST /actividad/crearActividad**
   - **Descripción:** Crea una nueva actividad dentro de una comunidad.

2. **GET /actividad/getActividad**
   - **Descripción:** Obtiene los detalles de una actividad por su `id`.

3. **PUT /actividad/updateActividad**
   - **Descripción:** Actualiza los detalles de una actividad.

4. **DELETE /actividad/deleteActividad**
   - **Descripción:** Elimina una actividad existente.

---

## Lógica de Negocio

### Service de Usuarios
- Los nombres de los usuarios deberan tener una longitud de entre 5 y 20 caracteres, asií como la contraseña
-  Los roles no pueden ser nulos (En caso de ser nulo se le adjudica un 'USER')
- Se permiten usuarios sin comunidades y actividades asociadas, por lo tanto, se permite que sea nulo
- No se puede eliminar un usuario que tenga en su disposicion una comunidad

### Service de Comunidades
- El nombre de la comunidad no puede ser nulo, ni superar los 30 caracteres
- La descripcion de la comunidad se permite que sea nula, pero no superior a 100 caracteres
- La comunidad debe tener un creador con un id valido (existente)
- Las actividades tiene que tener una id valida (existente)
- La fecha de creación no puede ser nula

### Service de Actividades
- El nombre de una actividad no puede exceder los 30 caracteres, así como no puede ser nula
- La descripcion de una actividad no puede exceder los 200 caraceteres
- El lugar de una actividad no puede ser nulo, ni exceder los 30 caracteres
- El organizador de la actividad tiene que tener un id valido
- Los participantes tienen que tener un id valido
- La comunidad tiene que tener un id valido
---

## Excepciones y Códigos de Estado

1. **400 Bad Request:** Cuando los datos enviados son inválidos o incompletos (por ejemplo, al crear una comunidad o actividad sin la información necesaria).
   
2. **401 Unauthorized:** Cuando el usuario no está autenticado o el token JWT es inválido.

3. **403 Forbidden:** Cuando un usuario intenta realizar una acción para la cual no tiene permisos (por ejemplo, un usuario común intentando eliminar una comunidad o actividad).

4. **404 Not Found:** Cuando el recurso solicitado no existe (por ejemplo, una comunidad o actividad con un `id` no válido).

---

## Restricciones de Seguridad

1. **Autenticación JWT:** Todos los endpoints que requieran acceso restringido estarán protegidos por autenticación JWT. Los usuarios deberán proporcionar un token válido (ADMIN) para acceder a estas rutas.

2. **Roles de Usuario:**
   - Los usuarios pueden tener roles como `USER` o `ADMIN`.
   - Solo los usuarios con el rol `ADMIN` pueden crear, actualizar o eliminar comunidades y actividades.

3. **Cifrado de Contraseñas:** Las contraseñas de los usuarios se cifran antes de almacenarse en la base de datos.

4. **Validación de Datos:** Todos los datos de entrada serán validados para evitar errores de formato. Esto incluye la validación de campos obligatorios y de evitar registros duplicados

