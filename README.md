# ForoHub Alura Challenge Backend
Desafío Alura Latam - Oracle One
Crear un foro implementando CRUD, SOLID, API REST, Java Spring Boot y Spring Security

# ¡Bienvenido a nuestro último desafío Challenge Back End!

Un foro es un espacio donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados tópicos.

Ya sabemos para qué sirve el foro y conocemos su aspecto, pero ¿sabemos cómo funciona detrás de escena? Es decir, ¿dónde se almacenan las informaciones? ¿Cómo se tratan los datos para relacionar un tópico con una respuesta, o cómo se relacionan los usuarios con las respuestas de un tópico?

Este es nuestro desafío, llamado ForoHub Alura: en él, vamos a replicar este proceso a nivel de back end y, para eso crearemos una API REST usando Spring.

# Funcionalidades de la API
La API debe permitir a los usuarios:

- Crear un nuevo tópico
- Mostrar todos los tópicos creados
- Mostrar un tópico específico
- Actualizar un tópico
- Eliminar un tópico
(Se implementa otras funciones como la de crear un nuevo usuario, login, modificar datos del usuario y eliminación lógica de usuario)

## Objetivos del Challenge
Implementar una API REST con las siguientes funcionalidades:
API con rutas implementadas siguiendo las mejores prácticas del modelo REST.
Validaciones realizadas según las reglas de negocio.
Implementación de una base de datos relacional para la persistencia de la información.
Servicio de autenticación/autorización para restringir el acceso a la información.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Validation
- MySQL
- Flyway Migration
- GitHub
- InteliJ Idea
- Trello
- Postman
- Insomnia

## Tablas Utilizadas 

- Usuario: Para permanencia de los datos de cada usuario que interactuarán en el ForoHub.
- Tópico: Es donde se publicarán los distintos tópicos con un título, mensaje y fecha de publicación. Antes de guardar cada tópico se verifica que el título y el mensaje no se repitan dentro de la tabla.
- Respuesta: Permite responder a cada tópico.
- Curso: Cada tópico puede pertenecer a un curso en particular.
- Perfil: Aún no estoy decidido si implementar esta tabla, en caso de hacerlo actualizaré esta descripción.

## Configuración
Cuando descargue o clone este repositorio, primero debe crear una base de datos en MySQL llamada alura_foro_api. Después debe configurar las siguientes variables de entorno en su sistema:

- ${DB_HOST}: Por defecto localhost
- ${DB_NAME}: Nombre de la base de datos
- ${DB_USER}: El usuario definido en MySQL
- ${DB_PASS}: Contraseña definida en MySQL

## Desarrollo de cada Controlador

### Controladores de Usuarios

Está dividido en dos rutas: /user y /login. Estos endpoints no solicitan autenticación en solicitudes POST, el resto de las solicitudes deberán ser autenticadas mediante un TOKEN.

- Registrar un nuevo usuario: Método POST en la URL localhost:8080/user, en el cuerpo del JSON debe enviar:
  - nombre
  - email
  - password: Deberá ingresar de 8 a 12 caracteres, al menos: un número, una letra mayúscula y una letra minúscula, acepta caracteres especiales pero no es obligatorio 
  
  Estos campos son obligatorios, en caso de recibir alguno en blanco o nulo retorna un mensaje de error informando cuáles son los campos faltantes. También verifica si el email no está repetido dentro de la base de datos, en caso afirmativo también informa sobre este inconveniente. Una vez generado el nuevo usuario retorna un mensaje de éxito.

- Login: Método POST en la URL localhost:8080/login, se debe enviar un JSON con los siguientes campos:
  - email
  - password
  
  Estos campos son obligatorios, en caso de recibir alguno en blanco o vacío retorna un mensaje de error indicando cuál es el dato faltante. La API busca el email en el sistema y si no lo encuentra retorna un mensaje indicando dicho inconveniente, en caso positivo verifica que la contraseña brindada sea la correcta, de lo contrario informa el error. Una vez validados los datos, genera y retorna el TOKEN que será utilizado para autorizar el resto de las tareas concernientes del Foro Hub.

- Modificación de contraseña: La validación de login se hace mediante TOKEN, si el toquen es válido permite modificar la contraseña
(falta filtrar cuando el token expiró)

### Controladores de Tópicos

Ruta: /topicos: El usuario debe estar autenticado mediante TOKEN para poder utilizar los metodos de esta ruta

Detales de la tabla "topicos":

titulo: Almacena el título del tópico.
mensaje: Cuerpo del tópico.
fecha_creacion: Fecha y hora en la que fué creado el tópico, generado en forma automatica por el sistema.
estado: Identifica si el tópico fue resuelta o no.
autor: Nombre del autor del tópico.
curso: Curso al cual hace referencia el tópico.

### Método POST en URL localhost:8080/topicos, se debe enviar los siguientes campos

Datos que recibe:
- titulo
- mensaje
- autor
- curso


Todos los datos son obligatorios, y son validados por el sistema, por defecto se asigna el valor false a campo estado. Además verifica que el título y el mensaje no estén duplicados en la base de datos.

### Método GET en URL localhost:8080/topicos
Devuelve los siguientes datos:
- id
- titulo
- mensaje
- estado
- autor: nombre del autor
- curso: nombre del curso

Por defecto se lista todos los tópicos ordenados por fecha de creación en orden ascendente

### Método GET en URL localhost:8080/topico/{id}
Muestra los tópicos de usuario reterminado por {id}

### Método GET en URL localhost:8080/topico/curso/{curso_id}
Muestra los tópicos de un curso en particular determinado por {curso_id}

### Método GET en URL localhost:8080/topico/year/{year}
Muestra los tópicos de un año determinado por {year}

### Método PUT en URL localgost:8080/topico
Permite modificar el título y/o el mensaje de un tópico, los datos que recibe la ruta es:
- id: La aplicación verifica si existe el tópico, cuando no lo encuentra envía el correspondiente mensaje de error.
- titulo: Si está vacío no se modifica el titulo del tópico
- mensaje: Si está vacío no se modifica el mensaje del tópico

Si los ambos campos estás vacíos envía un mensaje indicando que no se ha recibido datos para modificar el tópico.

### Método DELETE en URL localhost:8080/topico/{id}
Busca el tópico {id} y realiza una eliminación del tópico, en caso de no encontrar el {id} envía un mensaje de error.
