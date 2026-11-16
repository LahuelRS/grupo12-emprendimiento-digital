## Día 1 (Lunes 09/11):
  Se logró contactar a los integrantes (de 4 quedamos tres). Iniciamos con la elección de la idea, abriendo el repositorio en Github y el tablero en Trello.

## Dia 2 (Martes 10/11):
  Finalizamos la distribucion de tareas y coordinamos los metodos compartidos entre los integrantes correspondientes. Actualizacion del tablero en Trello.
  
## Día 3 (Miercoles 11/11)

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

## Día 4 (Jueves 12/11)

**Sacco:** corrigió la llave de cierre de `Pelicula.java` e incorporó el getter `getCopiasTotales()`. Amplió la clase `Catalogo` implementando el método `eliminarPelicula`, que permite eliminar películas por código, rechazando la operación si la película no existe o tiene copias alquiladas. Desarrolló `buscarPorTitulo`, utilizando `List<Pelicula>` y un recorrido del `HashMap` para encontrar coincidencias parciales sin distinguir mayúsculas y minúsculas. Incorporó `getPeliculas`, que devuelve una vista no modificable del mapa mediante `Collections.unmodifiableMap()`, permitiendo su consulta desde el módulo de reportes. Finalmente, actualizó las clases `Pelicula.java` y `Catalogo.java` en GitHub, junto con el contrato `catalogo.md`, incorporando los nuevos métodos y sus funcionalidades.

**Roman:** creó la clase Socio (DNI, nombre y deuda, con métodos para sumar un recargo y saldarla) y la clase Alquileres con sus tres mapas: socios por DNI, alquileres de cada socio con el día de retiro y contador de veces alquilada. Implementó el registro y la búsqueda de socios, rechazando DNI repetidos, y el método alquilar con sus seis validaciones (socio inexistente, deuda pendiente, máximo de 3 películas, película ya alquilada por ese socio, película inexistente y falta de copias); si pasa todas, descuenta una copia del catálogo y suma al contador. Actualizó el contrato con la estructura de mapas definitiva.

**Santi:** Conexión del Menú Principal: Se vinculó MenuPrincipal.java con las clases troncales Catalogo y Alquileres, permitiendo que la consola ejecute operaciones reales en lugar de mensajes simulados.
Módulo de Socios y Alquileres: Implementación completa de Socio.java y Alquileres.java, abarcando ABM de socios, control de deudas, límite de 3 películas simultáneas y validación de copias disponibles.
Sistema de Mora y Devoluciones: Configuración del plazo de 3 días y cálculo automático de recargos ($1500 por día de atraso) sumados a la cuenta del socio, junto con la opción de saldar deudas.

## Día 5 (Viernes 13/11)

- **Qué hicimos:**
  - Se completó el módulo de socios y alquileres.
  - Se implementó el módulo de reportes (`Reportes.java`).
  - Se agregó la precarga de datos de prueba.
  - Se realizaron pruebas funcionales del módulo de catálogo, verificando el registro, búsqueda, eliminación y control de disponibilidad de películas.
  - Se creó la carpeta `/pruebas` en GitHub y se incorporó `PruebaCatalogo.java` para registrar las verificaciones realizadas.

- **Quién hizo qué:**
  - **Juan Sacco:** Creación de la carpeta `/pruebas` en el repositorio e incorporación de `PruebaCatalogo.java`, una clase independiente para verificar las funcionalidades del catálogo. Se realizaron pruebas de registro de películas, validación de códigos duplicados, búsqueda por código y título, retiro y devolución de copias, eliminación de películas y consulta del mapa. Los resultados obtenidos coincidieron con los esperados. Se organizó el tablero Kanban incorporando tarjetas para las pruebas de cada módulo y se actualizó la captura correspondiente a la jornada.
  - **Lahuel Román:** Implementación de `devolver`, que calcula los días de atraso a partir del día de retiro y el plazo de 3 días, cobra $1500 por cada día, suma el recargo a la deuda del socio, quita la película de su registro y devuelve la copia al catálogo. También `pagarDeuda`, que salda la deuda del socio, y `alquileresDe`, que informa qué películas tiene en su poder. Actualización del contrato del módulo en `/docs/contratos/alquileres.md` con el cálculo del recargo y el estado final de implementación.
  - **Santiago Sánchez:** Implementación de `Reportes.java`, con el listado del catálogo ordenado alfabéticamente por título, el agrupamiento de películas por género con `TreeMap` y el ranking de películas más alquiladas, que lee el contador de transacciones. Precarga de 5 películas y 3 socios al iniciar la aplicación, para usar en las pruebas y en la defensa. Commit y push del código integrado a GitHub.

- **Qué nos trabó:**
  - Hubo que distinguir en `devolver` dos resultados que se podían confundir: devolver `0` significa que la devolución fue a tiempo, mientras que `-1` significa que ese socio no tenía alquilada esa película. El menú tiene que tratarlos distinto.
  - Fue necesario organizar la carpeta `/pruebas` de GitHub para mantener separados los archivos de pruebas del código principal del sistema.

- **Qué sigue:**
  - Completar la documentación de los casos de prueba en archivos Markdown dentro de `/pruebas`.
  - Verificar el funcionamiento integrado de los módulos de Catálogo, Socios, Alquileres y Reportes.
  - Revisar posibles errores y casos límite.
  - Continuar con la preparación de la documentación final y la defensa oral.

## Día 6 (Sábado 14/11)

- **Qué hicimos:**
  - Se escribieron y ejecutaron las pruebas funcionales de los módulos y se verificó el funcionamiento integrado del sistema.
  - Quedó completa la integración: el menú pasó a operar sobre las clases reales y se incorporó la clase `Reportes`, con lo que el proyecto quedó funcionando de punta a punta.
  - Se puso al día la documentación de avance de los días anteriores.
- **Quién hizo qué:**
  - **Juan Sacco:** Subió la captura del tablero y las entradas de bitácora de los días 4 y 5, y normalizó el nombre del archivo de requerimientos del catálogo según la consigna. Comenzó con la documentación de los casos de prueba del catálogo.
  - **Lahuel Román:** Desarrolló `PruebaAlquileres.java`, un programa con datos fijos que verifica el alta de socios, las seis validaciones del alquiler, la devolución dentro del plazo y con atraso, el cálculo del recargo, el bloqueo por deuda, el pago y las consultas por socio. Documentó los 24 casos en `pruebas-alquileres.md` con sus resultados esperados y obtenidos, y ejecutó la prueba integral del sistema recorriendo las opciones del menú.
  - **Santiago Sánchez:** Subió `MenuPrincipal.java` conectado a `Catalogo`, `Alquileres` y `Reportes`, con datos de prueba iniciales, la opción de pago de deuda y el manejo de entradas inválidas. También `Reportes.java`, con el catálogo alfabético, el ranking de más alquiladas y los alquileres por género, y `PruebaReportes.java` con sus pruebas.
- **Qué nos trabó:**
  - Hubo que coordinar el cierre de la integración con la disponibilidad de los integrantes durante el fin de semana, para que el sistema quedara completo antes del día de cierre.
  - El menú no se puede probar de forma automatizada como el resto, porque lee del teclado, así que se resolvió documentar sus casos como pruebas manuales y automatizar solamente la clase `Reportes`.
  - Al ejecutar la prueba integral se detectó que la opción de listar el catálogo muestra el código de la película dos veces, un detalle de formato que quedó para corregir.
- **Qué sigue:**
  - Subir la documentación de pruebas que falta: catálogo, menú y reportes, e integración.
  - Definir el nombre del emprendimiento y completar el README final con tecnologías, instrucciones de ejecución, capturas, pruebas y conclusiones.
  - Corregir el contrato del módulo de menú, que todavía indica que los reportes se arman dentro de `MenuPrincipal`.
  - Revisión final del repositorio y preparación de la defensa oral.
- **Captura del tablero:** kanban/dia-6.png

## Día 7 (Domingo 15/11)

- **Qué hicimos:**
  - Cierre del proyecto: se definió el nombre del emprendimiento, **Rebobinar**, se completó la documentación de pruebas de todos los módulos y se dejó el README en su versión final.
  - Revisión final del repositorio: el sistema compila desde cero, los contratos coinciden con el código y no queda ningún apartado pendiente.
- **Quién hizo qué:**
  - **Juan Sacco:** Documentó los 11 casos de prueba del catálogo en `pruebas/pruebas-catalogo.md`. Tomó y subió las capturas del sistema funcionando (menú, alquiler y devolución con recargo, y reportes) en `docs/capturas`, que se muestran en el README.
  - **Lahuel Román:** Subió la bitácora y la captura del día 6. Corrigió el listado del catálogo en `MenuPrincipal`, que mostraba el código de cada película dos veces, y repitió la prueba integral completa con el mismo resultado; la documentó en `pruebas/pruebas-integracion.md` (21 casos). Reescribió el README final: nombre del emprendimiento, funcionalidades marcadas según lo implementado, facturación movida a fuera de alcance, instrucciones de ejecución desde Eclipse y desde la línea de comandos, resumen de pruebas y conclusiones. Agregó el nombre Rebobinar al encabezado del menú.
  - **Santiago Sánchez:** Documentó las pruebas de su módulo en `pruebas/pruebas-menu-reportes.md`: 11 casos automatizados de `Reportes` (orden alfabético sin distinguir mayúsculas, títulos repetidos, empates en el ranking, reportes con el sistema vacío y película dada de baja) y 16 casos manuales del menú. Actualizó `docs/contratos/menu.md` para reflejar que los reportes viven en la clase `Reportes` y las opciones finales del menú.
- **Qué nos trabó:**
  - La conectividad de un integrante durante el fin de semana obligó a reordenar las tareas de cierre, pero el resto del equipo tomó la revisión final mientras tanto, y lo pendiente de su módulo se cerró en el día.
  - Al revisar los documentos contra el código apareció una diferencia: el contrato del menú describía los reportes dentro de `MenuPrincipal`, cuando en la implementación quedaron en una clase aparte, tal como se había acordado en el contrato general. Se corrigió el documento.
- **Qué sigue:**
  - Preparación de la defensa oral del jueves 19/11: cada integrante repasa su módulo y la justificación del uso de mapas, y practica modificar su código en vivo.
- **Captura del tablero:** kanban/dia-7.png

## Resumen final

El sistema Rebobinar quedó completo y funcionando por consola: catálogo de películas, socios y alquileres con recargo por atraso, pago de deuda y tres reportes ordenados con `TreeMap`. El repositorio contiene el código en `/src`, los requerimientos, contratos y diagrama de clases en `/docs`, cuatro documentos de pruebas con sus programas en `/pruebas`, y las capturas diarias del tablero en `/kanban`. Las funcionalidades planificadas se cumplieron, salvo la facturación de mostrador, que se dejó documentada como fuera de alcance.
