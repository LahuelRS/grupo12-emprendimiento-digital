Día 1 (Lunes 09/11):
  Se logró contactar a los integrantes (de 4 quedamos tres). Iniciamos con la elección de la idea, abriendo el repositorio en Github y el tablero en Trello.

Dia 2 (Martes 10/11):
  Finalizamos la distribucion de tareas y coordinamos los metodos compartidos entre los integrantes correspondientes. Actualizacion del tablero en Trello.
  
Día 3 (Miércoles 11/11):

- **Qué hicimos:** 
  - Se organizó la carpeta `/docs/contratos/` con el acuerdo general de conexión entre clases y el detalle de cada módulo.
  - Sacco y Román definieron los métodos públicos y las reglas de negocio del sistema (plazo de 3 días, recargo de $1500 por día de atraso, máximo 3 películas por socio y bloqueo por deuda), además de codificar parte de las clases `Pelicula`, `Catalogo`, `Socio` y `Alquileres`.
  - Sanchez subió el documento de requerimientos del menú, el contrato de su módulo y el diagrama de clases.
- **Quién hizo qué:**
  - **Juan Sacco:** Creación de la carpeta `/src`, desarrollo de las clases `Pelicula` y `Catalogo` con sus métodos de búsqueda, retiro y devolución de copias, y creación del contrato general en GitHub.
  - **Lahuel Román:** Diseño de la arquitectura de contratos, definición de las reglas de negocio para préstamos/recargos, gestión de permisos en Trello y desarrollo del módulo de `Socio` y `Alquileres`. Movimiento de contratos a /docs/.
  - **Santiago Sanchez:** Documentación de requerimientos del menú, contrato de diseño de reportes/menú, diagrama de clases y armado base de `MenuPrincipal.java`.
- **Qué nos trabó:** 
  - Ajustes con la ubicación de las carpetas en GitHub (mover archivos a `/src` y `/docs`), permisos en el tablero de Trello y la definición de los nombres exactos de los métodos para que coincidan entre módulos.

Día 4 (Jueves 12/11):

**Sacco:** corrigió la llave de cierre de `Pelicula.java` e incorporó el getter `getCopiasTotales()`. Amplió la clase `Catalogo` implementando el método `eliminarPelicula`, que permite eliminar películas por código, rechazando la operación si la película no existe o tiene copias alquiladas. Desarrolló `buscarPorTitulo`, utilizando `List<Pelicula>` y un recorrido del `HashMap` para encontrar coincidencias parciales sin distinguir mayúsculas y minúsculas. Incorporó `getPeliculas`, que devuelve una vista no modificable del mapa mediante `Collections.unmodifiableMap()`, permitiendo su consulta desde el módulo de reportes. Finalmente, actualizó las clases `Pelicula.java` y `Catalogo.java` en GitHub, junto con el contrato `catalogo.md`, incorporando los nuevos métodos y sus funcionalidades.

**Roman:** creó la clase Socio (DNI, nombre y deuda, con métodos para sumar un recargo y saldarla) y la clase Alquileres con sus tres mapas: socios por DNI, alquileres de cada socio con el día de retiro y contador de veces alquilada. Implementó el registro y la búsqueda de socios, rechazando DNI repetidos, y el método alquilar con sus seis validaciones (socio inexistente, deuda pendiente, máximo de 3 películas, película ya alquilada por ese socio, película inexistente y falta de copias); si pasa todas, descuenta una copia del catálogo y suma al contador. Actualizó el contrato con la estructura de mapas definitiva.

**Santi:** Conexión del Menú Principal: Se vinculó MenuPrincipal.java con las clases troncales Catalogo y Alquileres, permitiendo que la consola ejecute operaciones reales en lugar de mensajes simulados.
Módulo de Socios y Alquileres: Implementación completa de Socio.java y Alquileres.java, abarcando ABM de socios, control de deudas, límite de 3 películas simultáneas y validación de copias disponibles.
Sistema de Mora y Devoluciones: Configuración del plazo de 3 días y cálculo automático de recargos ($1500 por día de atraso) sumados a la cuenta del socio, junto con la opción de saldar deudas.
