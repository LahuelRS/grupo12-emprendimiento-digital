# Contrato del módulo Socios y alquileres — Roman

## Clases responsables
- `Socio.java`: representa a un socio de la videoteca y su deuda.
- `Alquileres.java`: registra socios, alquileres y devoluciones utilizando mapas.

Ambas clases se encuentran en `/src`, dentro del paquete por defecto.

## Estructuras de datos
- `Map<String, Socio>`: DNI → socio.
- `Map<String, Map<Integer, Integer>>`: DNI → (código de película → día de retiro). El día de retiro se guarda para calcular el recargo en la devolución. Como la llave interna es el código, un socio no puede tener dos copias de la misma película al mismo tiempo.
- `Map<Integer, Integer>`: código de película → cantidad de veces alquilada.

## Métodos de Socio

| Método | Retorno | Descripción |
|---|---|---|
| `Socio(String dni, String nombre)` | constructor | Crea un socio sin deuda. |
| `getDni()` | `String` | Devuelve el DNI. |
| `getNombre()` | `String` | Devuelve el nombre. |
| `getDeuda()` | `int` | Devuelve la deuda acumulada en pesos. |
| `sumarDeuda(int monto)` | `void` | Suma un recargo a la deuda. Ignora los montos menores o iguales a cero. |
| `pagarDeuda()` | `void` | Deja la deuda en cero. |

## Métodos de Alquileres

Se crea con `new Alquileres(catalogo)`.

| Método | Retorno | Descripción |
|---|---|---|
| `agregarSocio(Socio socio)` | `boolean` | Registra un socio y crea su mapa de alquileres. Devuelve `false` si el DNI ya existe. |
| `buscarSocio(String dni)` | `Socio` | Devuelve el socio encontrado o `null` si no existe. |
| `alquilar(String dni, int codigo, int dia)` | `String` | Registra el alquiler. Devuelve `"OK"` o el motivo del rechazo. |
| `devolver(String dni, int codigo, int dia)` | `int` | Registra la devolución. Devuelve el recargo en pesos (0 si no hubo atraso) o `-1` si ese socio no tenía esa película. |
| `pagarDeuda(String dni)` | `boolean` | Deja la deuda del socio en cero. Devuelve `false` si el socio no existe. |
| `alquileresDe(String dni)` | `List<Integer>` | Devuelve los códigos que tiene alquilados el socio; lista vacía si no tiene. |
| `getVecesAlquilada()` | `Map<Integer, Integer>` | Devuelve la cantidad de alquileres por código, para los reportes. |

## Reglas que aplica
- Plazo de alquiler: 3 días.
- Recargo: 1500 pesos por cada día de atraso, que se suma a la deuda del socio.
- Máximo de películas por socio al mismo tiempo: 3.
- Un socio con deuda no puede alquilar hasta pagarla.

El plazo, el recargo y el máximo de películas están definidos como constantes `static final` al principio de `Alquileres`, porque son iguales para toda la videoteca y no cambian de un socio a otro.

## Motivos de rechazo de un alquiler
Se controlan en este orden y el método devuelve el primero que corresponda:

1. El socio no existe.
2. El socio tiene deuda.
3. El socio ya tiene el máximo de películas.
4. El socio ya tiene alquilada esa película.
5. La película no existe en el catálogo.
6. No hay copias disponibles.

La copia se retira del catálogo en último lugar, para no descontarla si alguna validación falla.

## Cálculo del recargo
`diasDeAtraso = dia de devolución − día de retiro − plazo`

Si el resultado es mayor que cero, el recargo es `diasDeAtraso × 1500` y se suma a la deuda del socio. Si es cero o negativo, la devolución fue a tiempo y el recargo es 0.

Ejemplo: una película retirada el día 10 vence el día 13. Devuelta el día 16, el atraso es de 3 días y el recargo de 4500 pesos.

El método distingue dos resultados que no hay que confundir: `0` significa devolución sin recargo y `-1` significa que ese socio no tenía esa película.

## Integración
- Con `Catalogo`: usa `buscarPorCodigo`, `retirarCopia` y `devolverCopia`. No accede al mapa privado del catálogo.
- Con `MenuPrincipal`: recibe el DNI, el código y el número de día por parámetro; no lee del teclado ni imprime.
- Con `Reportes`: entrega `getVecesAlquilada()` para el ranking y los alquileres por género.

## Estado de implementación
- Listo: las clases `Socio` y `Alquileres` completas, con el registro y la búsqueda de socios, el alquiler con sus seis validaciones, la devolución con cálculo de recargo, el pago de deuda y la consulta de películas por socio.
- Pendiente: las pruebas del módulo, en `/pruebas`.
