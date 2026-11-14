# Pruebas del módulo Socios y alquileres

Responsable: Roman (@LahuelRS)

Las pruebas están automatizadas en `PruebaAlquileres.java`. El programa carga cuatro películas
(Matrix con una sola copia, y Titanic, Toy Story y Alien con dos cada una) y dos socios,
y después ejecuta los casos en el orden de la tabla. No usa teclado: los datos están fijos
en el código para que el resultado sea siempre el mismo.

Reglas que se verifican: plazo de 3 días, recargo de 1500 pesos por día de atraso,
máximo de 3 películas por socio y bloqueo del socio con deuda.

## Casos de prueba

| N° | Qué pruebo | Datos de entrada | Resultado esperado | Resultado obtenido | ¿Pasó? |
|----|------------|------------------|--------------------|--------------------|--------|
| 1 | Alta de un socio nuevo | DNI 30111222, Ana Perez | `true` | `true` | Sí |
| 2 | Alta de un segundo socio | DNI 27333444, Beto Gomez | `true` | `true` | Sí |
| 3 | Alta con un DNI que ya existe | DNI 30111222 | `false` | `false` | Sí |
| 4 | Buscar un DNI inexistente | DNI 11111111 | `null` | `null` | Sí |
| 5 | Alquilar con un socio no registrado | DNI 11111111, película 101 | El socio no esta registrado | El socio no esta registrado | Sí |
| 6 | Alquilar una película inexistente | DNI 30111222, película 999 | La pelicula no existe en el catalogo | La pelicula no existe en el catalogo | Sí |
| 7 | Alquiler válido | Ana, Matrix (101), día 1 | `OK` y Matrix queda con 0 copias | `OK`, copias disponibles 0 | Sí |
| 8 | Alquilar una película que el socio ya tiene | Ana, Matrix otra vez | El socio ya tiene alquilada esa pelicula | El socio ya tiene alquilada esa pelicula | Sí |
| 9 | Alquilar sin copias disponibles | Beto, Matrix (única copia alquilada) | No hay copias disponibles de Matrix | No hay copias disponibles de Matrix | Sí |
| 10 | Segundo alquiler del mismo socio | Ana, Titanic (102), día 1 | `OK` | `OK` | Sí |
| 11 | Tercer alquiler del mismo socio | Ana, Toy Story (103), día 1 | `OK` | `OK` | Sí |
| 12 | Superar el máximo de 3 películas | Ana, Alien (104), día 1 | El socio ya tiene 3 peliculas alquiladas, y Ana sigue con 3 | El socio ya tiene 3 peliculas alquiladas; `[101, 102, 103]` | Sí |
| 13 | Devolver dentro del plazo | Ana devuelve Matrix el día 4 (retiró el día 1) | Recargo `0` y la copia vuelve al catálogo | `0`, copias disponibles 1 | Sí |
| 14 | Devolver una película ya devuelta | Ana devuelve Matrix el día 5 | `-1` | `-1` | Sí |
| 15 | Devolver algo que el socio no tiene | Beto devuelve Titanic | `-1` | `-1` | Sí |
| 16 | Devolver con atraso | Beto retira Alien el día 10 y la devuelve el día 16 | Recargo `4500` (3 días de atraso × 1500) sumado a la deuda | `4500`, deuda de Beto 4500 | Sí |
| 17 | Alquilar teniendo deuda | Beto, Titanic, día 17 | El socio tiene una deuda de 4500 pesos | El socio tiene una deuda de 4500 pesos | Sí |
| 18 | Pagar la deuda | DNI 27333444 | `true` y deuda en 0 | `true`, deuda 0 | Sí |
| 19 | Pagar la deuda de un DNI inexistente | DNI 11111111 | `false` | `false` | Sí |
| 20 | Alquilar después de pagar la deuda | Beto, Titanic, día 17 | `OK` | `OK` | Sí |
| 21 | Consultar las películas de un socio | DNI 27333444 | Solo Titanic, porque ya devolvió Alien | `[102]` | Sí |
| 22 | Consultar las de un socio inexistente | DNI 11111111 | Lista vacía | `[]` | Sí |
| 23 | La lista devuelta no afecta a los datos internos | Se vacía la lista obtenida y se vuelve a consultar | El socio conserva sus alquileres | `[102]` | Sí |
| 24 | Contador de veces alquiladas | Después de todos los alquileres | Titanic con 2 y el resto con 1 | `{101=1, 102=2, 103=1, 104=1}` | Sí |

## Resultado

Los 24 casos pasaron. No se detectaron errores en el módulo.

## Cómo ejecutar las pruebas

`PruebaAlquileres.java` necesita las clases de `/src` para compilar. En Eclipse, la forma más
simple es marcar la carpeta `pruebas` como carpeta de fuentes (clic derecho sobre la carpeta ›
Build Path › Use as Source Folder) y después ejecutar `PruebaAlquileres` con Run.

## Cálculo del recargo verificado en el caso 16

Beto retira Alien el día 10, con un plazo de 3 días, así que vence el día 13.
Al devolverla el día 16, el atraso es de `16 − 10 − 3 = 3` días, que multiplicados
por 1500 dan los 4500 pesos que quedan como deuda del socio.
