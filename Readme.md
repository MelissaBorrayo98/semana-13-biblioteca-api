# 📚 Actividad: Desarrollo y Pruebas de API REST e Interfaz Web - Sistema de Biblioteca (semana-13-biblioteca-api)

## 👤 Datos del Estudiante
- **Nombre:** Melissa Betzabeh Borrayo Mejía
- **Carné:** 9941-25-28099
- **Curso / Carrera:** Ingeniería en Sistemas de Información (Universidad Mariano Gálvez, Sede Portales)

---

## 📝 Descripción del Problema
El proyecto consiste en el desarrollo y validación de un sistema integral de biblioteca personal que combina una API RESTful construida en Spring Boot con una interfaz web interactiva en el frontend (HTML5, Bootstrap 5 y JavaScript con Fetch API), todo conectado a una base de datos relacional en PostgreSQL (`biblioteca_personal`). El objetivo principal es implementar y comprobar el ciclo completo de operaciones CRUD (crear, leer, actualizar y eliminar) en tiempo real, además de consultar el catálogo, registrar libros con validaciones de formulario, eliminar registros mediante modales de confirmación y gestionar la persistencia de datos directamente en PostgreSQL.

---

## 🛠️ Tecnologías Utilizadas
- **Lenguaje:** Java (JDK 17+)
- **Framework:** Spring Boot (Spring Web, Spring Data JPA)
- **Base de Datos:** PostgreSQL y pgAdmin 4
- **Frontend:** HTML5, CSS3, JavaScript (ES6+ / Fetch API), Bootstrap 5, FontAwesome
- **Gestor de Dependencias:** Maven
- **Herramienta de Pruebas:** Navegador Web (Chrome/Edge/Firefox) y Postman

---

## 📂 Estructura del Proyecto
El proyecto sigue una arquitectura organizada en capas e incluye la interfaz de usuario en la carpeta estática de Spring Boot:

semana-13-biblioteca-api
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── biblioteca
│       │           ├── controller
│       │           │   └── LibroController.java
│       │           ├── entity
│       │           │   └── Libro.java
│       │           ├── repository
│       │           │   └── LibroRepository.java
│       │           └── Semana13BibliotecaApiApplication.java
│       └── resources
│           ├── static
│           │   └── index.html       <-- Interfaz Web Frontend
│           └── application.properties
└── pom.xml

---

## ⚙️ Configuración General de la Base de Datos
El archivo `src/main/resources/application.properties` contiene la configuración de conexión a la base de datos local:

spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_personal
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

---

## 📋 Tabla de Endpoints y Operaciones Frontend

| Método HTTP | Endpoint | Operación en Interfaz Web | Descripción |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/libros` | Consulta Inicial / Cargar Catálogo | Obtiene y lista todos los libros registrados en la tabla dinámica. |
| **POST** | `/api/libros` | Registrar Nuevo Libro | Envía objeto JSON mediante `JSON.stringify()` desde el formulario validado. |
| **DELETE** | `/api/libros/{id}` | Eliminar Libro | Elimina permanentemente el registro tras la confirmación en el modal. |
| **GET** | `/api/libros/{id}` | Consulta Individual | Busca un libro específico por su ID. |
| **GET** | `/api/libros/categoria/{categoria}` | Filtrado por Categoría | Filtra y devuelve los libros pertenecientes a una categoría específica. |
| **GET** | `/api/libros/disponibles` | Filtrado por Disponibilidad | Obtiene únicamente los libros marcados como disponibles. |
| **PUT** | `/api/libros/{id}` | Actualización (Backend) | Mantiene el endpoint listo en el backend para futuras integraciones. |

---

## 💻 Funcionalidades de la Interfaz Web (`index.html`)

1. **Consulta Dinámica (`GET /api/libros`):**
    - Al cargar la página (`http://localhost:8080`), consulta automáticamente la API y despliega en la tabla las columnas: ID, Código, Título, Autor, Categoría, Año de publicación, Precio, Estado (Disponible / No disponible) y Botón de Acción.

2. **Formulario de Registro con Validaciones (`POST /api/libros`):**
    - Incluye campos obligatorios (`required`): Código, Título, Autor, Categoría, Año de publicación y Precio.
    - Aplica validaciones nativas de HTML5: `min="1"` para año, `min="0.01"` y `step="0.01"` para precio, además de límites de longitud (`maxlength`).
    - Envía solicitudes HTTP POST con `headers: { "Content-Type": "application/json" }` y `JSON.stringify(libro)`.
    - Tras un registro exitoso, muestra una alerta de éxito, limpia el formulario y actualiza la tabla automáticamente sin recargar la página.

3. **Eliminación con Modal de Confirmación (`DELETE /api/libros/{id}`):**
    - Botón interactivo de papelera en cada fila de la tabla.
    - Solicita confirmación explícita mediante una ventana modal.
    - Si el usuario confirma, ejecuta la petición `DELETE`, notifica el éxito, refresca la tabla y elimina el registro permanentemente en PostgreSQL.

4. **Manejo de Errores e Indicadores:**
    - Comprobación estricta del resultado mediante `response.ok`.
    - Muestra de alertas flotantes informativas ante operaciones exitosas o fallidas (por ejemplo, en caso de código duplicado o fallo de servidor).
    - Indicador dinámico de estado en la barra superior (*API Conectada / API Desconectada*).

---

## 🚀 Instrucciones para Ejecutar la Aplicación

1. **Clonar o descargar** el repositorio en tu entorno local.
2. **Configurar la base de datos:** Asegúrate de tener PostgreSQL ejecutándose y crea la base de datos llamada `biblioteca_personal`.
3. **Actualizar credenciales:** Modifica el archivo `application.properties` con tu usuario y contraseña de PostgreSQL.
4. **Abrir el proyecto** en tu IDE de preferencia (IntelliJ IDEA, Eclipse o VS Code).
5. **Ejecutar la aplicación** iniciando la clase principal `Semana13BibliotecaApiApplication.java` (o mediante terminal con `./mvnw spring-boot:run`).
6. **Abrir la Interfaz Web:** Abre tu navegador web e ingresa a:
   `http://localhost:8080`
