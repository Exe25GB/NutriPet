NutriPet - Ecosistema de Microservicios

    Descripción del Proyecto
Este proyecto consiste en un sistema backend empresarial basado en una arquitectura MVC distribuida de 10 microservicios, diseñado para la gestión comercial, logística y de ventas de NutriPet El sistema está desarrollado con Java y Spring Boot, separación de responsabilidades y conexión a una base de datos Oracle. Todo el ecosistema está configurado para comunicarse mediante APIs y peticiones HTTP.

    Estudiantes
EXEQUIEL JESUS GUZMAN BRIONES
HECTOR LEON FERNANDEZ
VICTOR ELIAS GODOY NAVARRETE

    Funcionalidades Implementadas
Hasta el momento, el sistema cuenta con los siguientes módulos centrales totalmente funcionales:

- MS-01 Usuarios: Su responsabilidad es la gestión de acceso y perfiles. Su lógica de negocio incluye el registro, login, encriptación de claves y asignación de roles (Admin, Operador, Cliente).
- MS-02 Productos: Actúa como el catálogo maestro de alimentos. Se encarga de la gestión de atributos de los productos (como marca, tipo de mascota y ciclo vital) y sus categorías.
- MS-03 Inventario: Es responsable del control de existencias y lotes. Su lógica de negocio se basa en la trazabilidad de fechas de vencimiento y la generación de alertas de caducidad para evitar pérdidas económicas.
- MS-04 Clientes: Su responsabilidad principal es la gestión de la base de datos de clientes. Su lógica de negocio consiste en la diferenciación automática entre clientes mayoristas y minoristas para poder aplicar distintas reglas de negocio.
- MS-05 Precios: Funciona como el motor de reglas financieras. Se encarga del cálculo dinámico de precios y de aplicar descuentos dependiendo del tipo de cliente (mayorista o minorista).
- MS-06 Pedidos: Su responsabilidad es la orquestación de las ventas. Su lógica de negocio contempla la creación de órdenes de compra y la actualización del stock en tiempo real para evitar quiebres de inventario.
- MS-07 Pagos: Responsable del procesamiento transaccional. Se encarga de la validación de los pagos, la generación de comprobantes y el cambio de estado de los pedidos a "Pagado".
- MS-08 Proveedores: Su responsabilidad es la gestión de abastecimiento. Su lógica de negocio incluye el registro de proveedores de alimentos y la gestión de las órdenes de reposición.
- MS-09 Entregas: Se encarga de actualizar el estado de los pedidos. Su regla de negocio principal es validar que un pedido no pueda marcarse como "En Camino" si no ha sido pagado previamente, para lo cual consulta al - MS-07 (Pagos).
- MS-10 Notificaciones: Responsable de la comunicación con el usuario. Se encarga del envío de alertas de vencimiento de stock dirigidas al staff, y de informar los estados de los pedidos a los clientes.

Nota: Todos los microservicios cuentan con validación estricta de datos de entrada (DTOs) y manejo global de excepciones para retornar respuestas HTTP.

    Pasos para Ejecutar
        El proyecto utiliza Maven, por lo que no necesitas tener Maven instalado globalmente en tu equipo para compilarlo o ejecutarlo.

            Requisitos Previos:
        - Java 21 o superior.
        - Base de datos de forma remota.

            Configuración de Base de Datos:
        Debes asegurarte de tener creado el usuario nutripet_bd en Oracle. Luego, actualiza el archivo 'application.properties' dentro de la carpeta 'src/main/resources' de cada microservicio con tus credenciales y tener el Wallet de la instancia: 
            spring.datasource.url= jdbc:oracle:thin:@df1_high?TNS_ADMIN=./Wallet_DF1
            spring.datasource.username= NutriPetBD_(segun ms)
            spring.datasource.password= tu_contraseña_aqui
