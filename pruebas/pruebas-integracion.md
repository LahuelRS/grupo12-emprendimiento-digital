# Prueba integral del sistema

Responsables: los tres integrantes.

Las pruebas de cada módulo verifican sus métodos por separado. Esta prueba verifica que los tres
módulos funcionen **juntos**: que el menú traduzca correctamente lo que devuelven las clases, que
un alquiler descuente una copia del catálogo, que una devolución la reponga y que los reportes
muestren lo que realmente pasó en el sistema.

Se ejecuta `MenuPrincipal` y se recorren las opciones en el orden de la tabla, sin reiniciar el
programa, porque cada caso se apoya en el estado que dejó el anterior.

Datos cargados al iniciar: Matrix (101, 2 copias), Titanic (102, 3), Toy Story (103, 2),
Alien (104, 1 copia) y El Padrino (105, 2); socios Ana Perez (30111222), Beto Gomez (27333444)
y Carla Diaz (35666777).

## Casos de prueba

| N° | Qué pruebo | Opción y datos | Resultado esperado | Resultado obtenido | ¿Pasó? |
|----|------------|----------------|--------------------|--------------------|--------|
| 1 | El sistema arranca con datos cargados | Opción 4 | Las 5 películas de prueba | Las 5 películas con sus copias | Sí |
| 2 | Alta de una película desde el menú | Opción 1: 106, Rocky, Drama, 1976, 2 copias | Confirmación del alta | Pelicula agregada correctamente. | Sí |
| 3 | El menú informa el rechazo del catálogo | Opción 1 con el código 106 otra vez | Aviso de código repetido | [Error] Ya existe una pelicula con el codigo 106. | Sí |
| 4 | Búsqueda por código | Opción 2: 106 | Los datos de Rocky | Rocky (Drama) \| codigo: 106 \| disponibles: 2 de 2 | Sí |
| 5 | Búsqueda por parte del título | Opción 2: roc | Encuentra Rocky igual | Rocky (Drama) \| codigo: 106 \| disponibles: 2 de 2 | Sí |
| 6 | Alta de socio desde el menú | Opción 5: 40999888, Dario Luna | Confirmación del alta | Socio registrado correctamente. | Sí |
| 7 | El menú informa el rechazo de socios | Opción 5 con el mismo DNI | Aviso de DNI repetido | [Error] Ya existe un socio con el DNI 40999888. | Sí |
| 8 | Alquiler que atraviesa los tres módulos | Opción 7: Ana, película 104, día 10 | Alquiler aceptado con la fecha de vencimiento | Alquiler registrado. Vence el dia 13. | Sí |
| 9 | **El alquiler descontó la copia en el catálogo** | Opción 4 | Alien pasa a 0 de 1 | Alien (Terror) \| disponibles: 0 de 1 | Sí |
| 10 | Otro socio pide la copia que ya no está | Opción 7: Beto, película 104, día 10 | Rechazo informando el título | [Rechazado] No hay copias disponibles de Alien | Sí |
| 11 | No se puede eliminar lo que está afuera | Opción 3: código 104 | Rechazo de la baja | [Error] No existe esa pelicula o tiene copias alquiladas. | Sí |
| 12 | Devolución con atraso y recargo | Opción 8: Ana, película 104, día 16 | Recargo de $4500 (3 días × 1500) y deuda actualizada | Recargo: $4500. Deuda total del socio: $4500. | Sí |
| 13 | **La deuda quedó registrada en el socio** | Opción 6: DNI 30111222 | Ana con $4500 de deuda y sin películas | Socio: Ana Perez \| deuda: $4500. No tiene peliculas alquiladas. | Sí |
| 14 | La deuda bloquea nuevos alquileres | Opción 7: Ana, película 101, día 17 | Rechazo por deuda | [Rechazado] El socio tiene una deuda de 4500 pesos | Sí |
| 15 | Pago de la deuda | Opción 9: DNI 30111222 | Confirmación del pago | Deuda de $4500 abonada. | Sí |
| 16 | El pago desbloquea al socio | Opción 7: Ana, película 101, día 17 | Alquiler aceptado | Alquiler registrado. Vence el dia 20. | Sí |
| 17 | Máximo de 3 películas por socio | Opción 7 con 102 y 103, y después con 105 | Las dos primeras aceptadas y la cuarta rechazada | Dos alquileres registrados y [Rechazado] El socio ya tiene 3 peliculas alquiladas | Sí |
| 18 | Devolución dentro del plazo | Opción 8: Ana, película 101, día 18 | Sin recargo | Devolucion registrada dentro del plazo. Sin recargo. | Sí |
| 19 | **Los reportes reflejan el estado real** | Opción 10 | Rocky aparece en el listado alfabético y las 4 películas alquiladas figuran en el ranking y por género | Catálogo alfabético con Rocky entre Matrix y Titanic; ranking con Matrix, Titanic, Toy Story y Alien; géneros Ciencia ficcion, Drama, Infantil y Terror, con 1 cada uno | Sí |
| 20 | Opción inexistente | 99 | Aviso sin cortar el programa | [Error] Opcion invalida. Ingrese un numero entre 0 y 10. | Sí |
| 21 | Salida ordenada | Opción 0 | Mensaje de despedida y fin del programa | Saliendo del sistema... Gracias por usar el programa! | Sí |

## Resultado

Los 21 casos pasaron. El sistema funciona de punta a punta: el menú, el catálogo, los socios,
los alquileres y los reportes trabajan con los mismos datos y se mantienen consistentes entre sí.

Durante la primera ejecución se detectó que la opción 4 mostraba el código de cada película dos
veces. Se corrigió `listarCatalogo` en `MenuPrincipal` y se repitió el recorrido completo con el
mismo resultado en los 21 casos.

Los casos 9, 13 y 19 son los que más interesan en esta prueba, porque son los que demuestran que
los módulos comparten el estado: una operación hecha desde el menú cambia el catálogo, el socio y
los reportes al mismo tiempo, sin que ninguna clase acceda a los datos internos de otra.

## Cómo repetir la prueba

Ejecutar `MenuPrincipal` desde `/src` y seguir la columna "Opción y datos" en orden. El programa
carga sus propios datos de prueba al iniciar, así que no hace falta preparar nada antes.
