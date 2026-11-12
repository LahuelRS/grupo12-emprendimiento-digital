# Contrato del módulo Socios y alquileres — Roman

## Clases responsables
- `Socio.java`: representa a un socio de la videoteca y su deuda.
- `Alquileres.java`: registra socios, alquileres y devoluciones utilizando mapas.

Ambas clases se encuentran en `/src`, dentro del paquete por defecto.

## Estructuras de datos
- `Map<String, Socio>`: DNI → socio.
- `Map<String, List<Integer>>`: DNI → códigos de las películas que tiene alquiladas.
- `Map<Integer, Integer>`: código de película → cantidad de veces alquilada.

## Métodos de Socio

| Método | Retorno | Descripción |
|---|---|---|
| `Socio(String dni, String nombre)` | constructor | Crea un socio sin deuda. |
| `getDni()` | `String` | Devuelve el DNI. |
| `getNombre()` | `String` | Devuelve el nombre. |
| `getDeuda()` | `int` | Devuelve la deuda acumulada en pesos. |

## Métodos de Alquileres

Se crea con `new Alquileres(catalogo)`.

| Método | Retorno | Descripción |
|---|---|---|
| `agregarSocio(Socio socio)` | `boolean` | Registra un socio. Devuelve `false` si el DNI ya existe. |
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

## Motivos de rechazo de un alquiler
- El socio no existe.
- La película no existe.
- No hay copias disponibles.
- El socio tiene deuda.
- El socio ya tiene el máximo de películas.

## Integración
- Con `Catalogo`: usa `buscarPorCodigo`, `retirarCopia` y `devolverCopia`. No accede al mapa privado del catálogo.
- Con `MenuPrincipal`: recibe el DNI, el código y el número de día por parámetro; no lee del teclado ni imprime.
- Con `Reportes`: entrega `getVecesAlquilada()` para el ranking y los alquileres por género.

## Pendiente de implementación
- Clases `Socio` y `Alquileres`.
- Pruebas de cada regla y de los casos de error.
