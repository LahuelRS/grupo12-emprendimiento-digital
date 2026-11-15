# Pruebas del módulo Menú, Reportes e Integración

Responsable: Sanchez (@santiisanchez6-png)

El módulo tiene dos partes que se prueban de manera distinta:

- **`Reportes`** se prueba de forma automatizada con `PruebaReportes.java`, porque no depende
  del teclado: recibe el catálogo y los alquileres y escribe el resultado por consola.
- **`MenuPrincipal`** se prueba de forma manual, recorriendo las opciones y comprobando lo que
  muestra cada una, porque es la parte que lee del teclado.

## Parte 1: pruebas automatizadas de Reportes

`PruebaReportes.java` carga a propósito las películas en un orden distinto del alfabético e
incluye dos títulos iguales (King Kong de 1933 y de 2005) para verificar que el `TreeMap` no
los pise entre sí.

| N° | Qué pruebo | Situación | Resultado esperado | Resultado obtenido | ¿Pasó? |
|----|------------|-----------|--------------------|--------------------|--------|
| 1 | Catálogo alfabético sin películas | Catálogo vacío | Aviso de catálogo vacío, sin error | El catalogo esta vacio. | Sí |
| 2 | Ranking sin alquileres | Sistema recién creado | Aviso de que no hay alquileres | Todavia no se registraron alquileres. | Sí |
| 3 | Alquileres por género sin alquileres | Sistema recién creado | Aviso de que no hay alquileres | Todavia no se registraron alquileres. | Sí |
| 4 | Orden alfabético | Cargadas como Toy Story, alien, King Kong, King Kong, Titanic | alien, King Kong, King Kong, Titanic, Toy Story | El mismo orden | Sí |
| 5 | El orden no distingue mayúsculas | "alien" en minúscula entre títulos con mayúscula | alien queda primera, no al final | alien aparece primera | Sí |
| 6 | Dos películas con el mismo título | King Kong con códigos 103 y 104 | Las dos aparecen en el listado | Las dos, con sus códigos | Sí |
| 7 | Ranking ordenado de mayor a menor | Toy Story 3, King Kong 2, alien 1, Titanic 1 | Ese mismo orden | 1) Toy Story: 3, 2) King Kong: 2, 3) alien: 1, 4) Titanic: 1 | Sí |
| 8 | Empates en el ranking | alien y Titanic con un alquiler cada una | Las dos aparecen, ninguna se pierde | Las dos, en los puestos 3 y 4 | Sí |
| 9 | Acumulado por género | Aventura 2, Drama 1, Infantil 3, Terror 1 | Esos totales, en orden alfabético | Aventura: 2, Drama: 1, Infantil: 3, Terror: 1 | Sí |
| 10 | Película dada de baja en el ranking | Se devuelve Titanic y se elimina del catálogo | El alquiler sigue contado, sin romper el reporte | Pelicula dada de baja (codigo 105): 1 alquiler(es) | Sí |
| 11 | Los tres reportes juntos | `mostrarTodos()` | Los tres, uno abajo del otro | Los tres en orden | Sí |

### Observación del caso 10

Cuando una película se elimina del catálogo, el ranking la sigue mostrando como "Pelicula dada
de baja" porque el contador de alquileres guarda el código, no el objeto. En cambio, el reporte
por género deja de contarla, porque el género es un dato de la película y esa película ya no
está en el catálogo. Es el comportamiento buscado: el historial de alquileres no se pierde,
pero un género sin películas no aparece en el listado.

## Parte 2: pruebas manuales del menú

Se ejecuta `MenuPrincipal` y se recorren las opciones. Los datos de prueba que carga el
programa al iniciar son 5 películas y 3 socios.

| N° | Qué pruebo | Qué ingreso | Resultado esperado | Resultado obtenido | ¿Pasó? |
|----|------------|-------------|--------------------|--------------------|--------|
| 12 | Letras donde va un número | `hola` en el menú | Aviso de error y vuelve a pedir el dato | [Error] Debe ingresar un numero entero. Intente de nuevo. | Sí |
| 13 | Opción que no existe | `99` | Aviso de opción inválida, sin cortar el programa | [Error] Opcion invalida. Ingrese un numero entre 0 y 10. | Sí |
| 14 | Listar el catálogo | Opción 4 | Las 5 películas con código, género y copias | Listado completo | Sí |
| 15 | Alta de película con código repetido | Opción 1, código 101 | Rechazo informando el código | [Error] Ya existe una pelicula con el codigo 101. | Sí |
| 16 | Buscar por código | Opción 2, `101` | Los datos de Matrix | Matrix (Ciencia ficcion) \| codigo: 101 | Sí |
| 17 | Buscar por parte del título | Opción 2, `matrix` | Encuentra Matrix sin importar mayúsculas | Matrix (Ciencia ficcion) \| codigo: 101 | Sí |
| 18 | Alta de socio con DNI repetido | Opción 5, DNI 30111222 | Rechazo informando el DNI | [Error] Ya existe un socio con el DNI 30111222. | Sí |
| 19 | Alquiler válido | Opción 7, DNI 30111222, película 101, día 1 | Confirmación con el día de vencimiento | Alquiler registrado. Vence el dia 4. | Sí |
| 20 | Eliminar una película alquilada | Opción 3, código 101 | Rechazo | [Error] No existe esa pelicula o tiene copias alquiladas. | Sí |
| 21 | Alquilar sin copias disponibles | Opción 7, otro socio, película 104 | Rechazo informando el título | [Rechazado] No hay copias disponibles de Alien | Sí |
| 22 | Devolución con atraso | Opción 8, retiro día 10 y devolución día 16 | Recargo de $4500 y deuda actualizada | Recargo: $4500. Deuda total del socio: $4500. | Sí |
| 23 | Alquilar con deuda | Opción 7 con ese socio | Rechazo por deuda | [Rechazado] El socio tiene una deuda de 4500 pesos | Sí |
| 24 | Pagar la deuda | Opción 9 con ese socio | Confirmación y deuda en cero | Deuda de $4500 abonada. | Sí |
| 25 | Alquilar después de pagar | Opción 7 con ese socio | Alquiler aceptado | Alquiler registrado. | Sí |
| 26 | Consultar un socio | Opción 6, DNI 27333444 | Nombre, deuda y películas en su poder | Socio, deuda $0 y el listado de sus películas | Sí |
| 27 | Salir del programa | Opción 0 | Mensaje de salida y fin | Saliendo del sistema... Gracias por usar el programa! | Sí |

## Resultado

Los 27 casos pasaron. No se detectaron errores en el módulo.

## Cómo ejecutar las pruebas automatizadas

`PruebaReportes.java` necesita las clases de `/src` para compilar. En Eclipse, la forma más
simple es marcar la carpeta `pruebas` como carpeta de fuentes (clic derecho sobre la carpeta ›
Build Path › Use as Source Folder) y después ejecutar `PruebaReportes` con Run.
## Parte 2: Pruebas manuales de Menú Principal (`MenuPrincipal.java`)

| N° | Qué pruebo | Situación | Resultado esperado | Resultado obtenido | ¿Pasó? |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | Inicio del sistema | Carga de datos de prueba iniciales | Menú principal operativo | Muestra opciones correctamente | Sí |
| 2 | Encabezado | Ver nombre del emprendimiento | Muestra "Rebobinar" | Aparece "Rebobinar" en pantalla | Sí |
| 3 | Opción Catálogo | Ingresar al submódulo | Despliega opciones de catálogo | Muestra menú de catálogo | Sí |
| 4 | Opción Alquileres | Ingresar al submódulo | Despliega opciones de alquiler | Muestra menú de alquileres | Sí |
| 5 | Opción Reportes | Ingresar al submódulo | Despliega opciones de reportes | Muestra menú de reportes | Sí |
| 6 | Error de ingreso (letra) | Ingresar letras en vez de números | Captura excepción sin caerse | Muestra aviso de opción inválida | Sí |
| 7 | Opción fuera de rango | Ingresar número 99 | Alerta opción inválida y repite bucle | Vuelve a pedir opción válida | Sí |
| 8 | Retorno de catálogo | Volver al menú principal | Regresa al menú principal | Vuelve al menú con éxito | Sí |
| 9 | Retorno de alquileres | Volver al menú principal | Regresa al menú principal | Vuelve al menú con éxito | Sí |
| 10 | Retorno de reportes | Volver al menú principal | Regresa al menú principal | Vuelve al menú con éxito | Sí |
| 11 | Alquiler de copia | Alquilar copia disponible a socio | Registra préstamo y descuenta stock | Préstamo registrado OK | Sí |
| 12 | Devolución con recargo | Devolver película fuera de término | Aplica recargo de $1500 por día | Calcula multa correctamente | Sí |
| 13 | Pago de deuda | Abonar saldo pendiente de socio | Actualiza estado de cuenta del socio | Deuda saldada con éxito | Sí |
| 14 | Visualizar reportes | Consultar listados desde el menú | Imprime reportes ordenados | Muestra datos por consola | Sí |
| 15 | Salida del sistema | Seleccionar opción de salida | Cierra la ejecución limpiamente | Finaliza programa sin errores | Sí |
| 16 | Persistencia de sesión | Mantener flujo interactivo activo | No pierde estado durante la ejecución | Sesión estable | Sí |
