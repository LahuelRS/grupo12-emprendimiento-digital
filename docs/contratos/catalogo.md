# Contrato del módulo Catálogo — Sacco

## Clases responsables

- `Pelicula.java`: representa una película y administra sus copias.
- `Catalogo.java`: almacena y administra las películas utilizando `Map<Integer, Pelicula>`.

Ambas clases se encuentran en `/src`, dentro del paquete por defecto.

## Métodos de Pelicula

| Método | Retorno | Descripción |
|---|---|---|
| `getCodigo()` | `int` | Devuelve el código de la película. |
| `getTitulo()` | `String` | Devuelve el título. |
| `getGenero()` | `String` | Devuelve el género. |
| `getCopiasTotales()` | `int` | Devuelve la cantidad total de copias. |
| `getCopiasDisponibles()` | `int` | Devuelve la cantidad de copias disponibles. |
| `descontarCopia()` | `boolean` | Descuenta una copia si hay disponibilidad. |
| `devolverCopia()` | `boolean` | Recupera una copia sin superar el total. |

## Métodos de Catalogo

| Método | Retorno | Descripción |
|---|---|---|
| `agregarPelicula(Pelicula pelicula)` | `boolean` | Agrega una película si su código no está registrado. Devuelve `false` si ya existe. |
| `buscarPorCodigo(int codigo)` | `Pelicula` | Devuelve la película encontrada o `null` si no existe. |
| `buscarPorTitulo(String titulo)` | `List<Pelicula>` | Devuelve una lista con las películas cuyo título contiene el texto buscado, sin distinguir mayúsculas y minúsculas. Si no encuentra coincidencias, devuelve una lista vacía. |
| `eliminarPelicula(int codigo)` | `boolean` | Elimina una película si existe y no tiene copias alquiladas. Devuelve `false` si no existe o tiene copias alquiladas. |
| `retirarCopia(int codigo)` | `boolean` | Descuenta una copia disponible. Devuelve `false` si no existe la película o no hay copias. |
| `devolverCopia(int codigo)` | `boolean` | Recupera una copia. Devuelve `false` si no existe la película o ya están todas disponibles. |
| `getPeliculas()` | `Map<Integer, Pelicula>` | Devuelve una vista no modificable del mapa para que el módulo de Reportes pueda consultar las películas registradas. |

## Integración con Alquileres

El módulo `Alquileres` utilizará los métodos públicos de `Catalogo` para buscar películas y modificar la disponibilidad.

No accederá directamente al `HashMap` privado del catálogo.

Los métodos compartidos son:

- `buscarPorCodigo(int codigo)`
- `retirarCopia(int codigo)`
- `devolverCopia(int codigo)`

## Integración con Menú y Reportes

El módulo `MenuPrincipal` utilizará los métodos públicos de `Catalogo` para registrar, buscar, listar y eliminar películas.

El módulo `Reportes` podrá utilizar `getPeliculas()` para consultar las películas almacenadas.

El mapa se devuelve mediante `Collections.unmodifiableMap(peliculas)`, evitando que otros módulos agreguen o eliminen entradas directamente desde la vista recibida.

Esta protección no impide modificar los objetos `Pelicula` mediante sus métodos públicos.

## Pruebas realizadas

Se creó una clase independiente `PruebaCatalogo.java` para verificar el funcionamiento del módulo.

Se ejecutaron correctamente las siguientes pruebas:

- Registro de películas.
- Validación de códigos duplicados.
- Búsqueda por código.
- Búsqueda parcial por título.
- Retiro de copias disponibles.
- Bloqueo de eliminación de películas con copias alquiladas.
- Devolución de copias.
- Eliminación de películas disponibles.
- Consulta de las películas registradas.

Los resultados obtenidos coincidieron con los esperados en estas pruebas.

## Pendiente

- Probar casos límite adicionales, como retirar una copia sin disponibilidad o devolver una copia cuando ya están todas disponibles.
- Incorporar validaciones para evitar cantidades negativas de copias y entradas inválidas.
- Registrar los casos de prueba en la carpeta `/pruebas`.
- Integrar el módulo con `Alquileres`, `MenuPrincipal` y `Reportes`.

## Estado

Funcionalidades principales del catálogo implementadas y probadas de manera independiente en Eclipse.

Pendiente de pruebas adicionales, documentación final e integración con los demás módulos.
