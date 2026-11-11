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
| `getCopiasDisponibles()` | `int` | Devuelve la cantidad de copias disponibles. |
| `descontarCopia()` | `boolean` | Descuenta una copia si hay disponibilidad. |
| `devolverCopia()` | `boolean` | Recupera una copia sin superar el total. |

## Métodos de Catalogo

| Método | Retorno | Descripción |
|---|---|---|
| `agregarPelicula(Pelicula pelicula)` | `boolean` | Agrega una película si su código no está registrado. |
| `buscarPorCodigo(int codigo)` | `Pelicula` | Devuelve la película encontrada o `null` si no existe. |
| `retirarCopia(int codigo)` | `boolean` | Descuenta una copia disponible. Devuelve `false` si no existe la película o no hay copias. |
| `devolverCopia(int codigo)` | `boolean` | Recupera una copia. Devuelve `false` si no existe la película o ya están todas disponibles. |

## Integración con Alquileres

El módulo `Alquileres` utilizará los métodos públicos de `Catalogo` para buscar películas y modificar la disponibilidad.

No accederá directamente al `HashMap` privado del catálogo.

## Pendiente de implementación

- Eliminación de películas.
- Búsqueda por título.
- Listado y consulta del catálogo para reportes.
- Validaciones adicionales y pruebas de funcionamiento.

## Estado

Implementación inicial desarrollada. Pendiente de pruebas e integración con los demás módulos.
