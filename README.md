# BackEnd Clinica Odontológica

El proyecto está orientado principlamente al desarrollo BackEnd, sin embargo se ha integrado a un FrontEnd básico para permitir la interacción entre el FrontEnd con el BackEnd.

El proyecto es una aplicación web de gestión para una clínica odontológica. Proporciona funcionalidades para administrar pacientes, odontólogos y turnos. Los usuarios pueden realizar las siguientes acciones:
- Gestión de Pacientes: Los usuarios con rol de Admin pueden ver la lista de pacientes existentes, agregar nuevos pacientes, actualizar pacientes existentes y eliminar pacientes.
- Gestión de Odontólogos: Los usuarios con rol de Admin pueden ver la lista de odontólogos disponibles, agregar nuevos odontólogos, actualizar odontólogos existentes y eliminar algún registro de odontólogo.
- Gestión de Turnos: Los usuarios con cualquier rol (Admin o User) pueden ver la lista de turnos programados, agregar nuevos turnos empleando el Id de pacientes y odontólogos, actualizar y eliminar turnos.
  

Tecnologías BackEnd utilizadas:
- Java v17: lenguaje principal del desarrollo BackEnd.
- Spring Framework (Spring Boot v2.7.15): Utilizado en todo el proyecto para la construcción de la aplicación web
- Spring Web: Empleado para crear controladores RESTful que gestionan las solicitudes HTTP entrantes y salientes.
- Spring Data JPA: Empleado para simplificar el acceso a la base de datos mediante el uso de anotaciones. Proporcionar una abstracción de alto nivel sobre la capa de persistencia, facilitando la interacción con la base de datos.
- Hibernate: marco de mapeo objeto-relacional (ORM) para Java. Proveedor de persistencia para la interacción con la base de datos.
- H2 Database v2.2.220: Sistema de gestión de bases de datos en memoria utilizado para las pruebas y desarrollo.
- API RESTful:El proyecto sigue las mejores prácticas para crear una API RESTful permitiendo exponer recursos y operaciones a través de una API HTTP, lo que significa que utiliza solicitudes HTTP (GET, POST, PUT, DELETE) que realizan operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en recursos como pacientes, odontólogos y turnos.
- Spring Security: Empleado para implementar la seguridad y autenticación, el control de acceso basado en roles y cifrado de contraseñas.
- junit v5.9.3: Se han implementado en las pruebas unitarias en las clases OdontologoServiceTest y PacienteServiceTest, y en las pruebas de integración en IntegracionTurnoTest. Estas pruebas son una práctica de desarrollo orientada a pruebas (Test-Driven Development, TDD) y garantizan la calidad del código.
- Lombok: Biblioteca que simplifica la creación de clases Java mediante la generación automática de getters, setters, constructores y otros métodos de utilidad. Ayuda a reducir la cantidad de código boilerplate en las clases de entidad.
- log4j v1.2.17: Empleado para realizar un seguimiento detallado de eventos y mensajes de registro en el desarrollo de la aplicación (logging).

Tecnologías FrontEnd utilizadas:
- HTML5: El proyecto utiliza HTML para crear la estructura y el contenido.
- JavaScript (ECMAScript 5 y superior): Se empleó para agregar interactividad, realizar operaciones como enviar solicitudes a través de la API, manipular el DOM y responder a eventos del usuario.
- Bootstrap v5.3.1: Framework de diseño para mejorar la apariencia y la usabilidad del sitio web.
- Fetch API: Empleado para realizar el CRUD de solicitudes HTTP a las APIs del servidor, permitiendo obtener, crear, actualizar y eliminar datos de la base de datos.

Estrategias empleadas en el desarrollo de la aplicación web:

- Manejo de controladores para gestionar las solicitudes HTTP entrantes y procesar las operaciones correspondientes.
- Mapeo de URL a métodos en los controladores empleando anotaciones como @GetMapping, @PostMapping, @PutMapping y @DeleteMapping, lo que permite realizar acciones específicas en los recursos.
- Validación de Datos para garantizar que los datos ingresados sean correctos y cumplan con las condiciones necesarias. Por ejemplo, verificando si una matrícula de odontólogo o un DNI de paciente ya existen en el sistema antes de registrarlos nuevamente.
- Manejo de Excepciones personalizado utilizando clases como BadRequestException y ResourceNotFoundException. Esto ayuda a proporcionar respuestas HTTP adecuadas en caso de errores o excepciones.
- Manejo de Errores mediante un ControllerAdvice para manejar excepciones globales y proporcionar respuestas adecuadas al cliente en caso de errores.
- Relaciones entre Entidades como Odontólogos, Pacientes y Turnos. Los odontólogos pueden tener múltiples turnos, y los pacientes también pueden tener varios turnos asignados. Un turno no puede ser registrado sin un paciente y un odontólogo.
- Se implementaron medidas de seguridad como autenticación y autorización para proteger los datos de los pacientes y odontólogos.
- DTOs (Data Transfer Objects) para transferir datos entre el FrontEnd y el BackEnd, lo que ayuda a reducir la sobrecarga de transferencia de datos innecesarios.
- Persistencia de Datos empleando una base de datos embebida H2 para almacenar datos relacionados con odontólogos, pacientes y turnos.
- Utilización de interfaces de repositorio mediante Spring Data JPA Repositories (OdontologoRepository, PacienteRepository, TurnoRepository, y UsuarioRepository) para gestionar entidades y realizar consultas a la base de datos sin la necesidad de escribir SQL manualmente.
- Inyección de Dependencias (DI) permitiendo la gestión eficiente de componentes, mejorando la modularidad y la flexibilidad de la aplicación al proporcionar a los objetos las dependencias que requieren, en lugar de crearlas directamente.
