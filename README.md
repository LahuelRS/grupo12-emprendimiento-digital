# Grupo 12: Videoteca (nombre a definir)

Proyecto integrador de Algoritmos y Estructuras de Datos 2 (AED2), ISSD, 2026.
Docente: Prof. Silvana Burgos.

**Integrantes**

| Apellido | Usuario de GitHub |
|---|---|
| Roman | [@LahuelRS](https://github.com/LahuelRS) |
| Sacco | [@JuanDSacco](https://github.com/JuanDSacco) |
| Sanchez | [@santiisanchez6-png](https://github.com/santiisanchez6-png) |

**Tablero Kanban:** https://trello.com/b/RaQfvhnN/grupo12-emprendimiento-digital

## Emprendimiento
Una videoteca de barrio al estilo Blockbuster: alquila películas a sus socios por unos días y cobra un recargo si se devuelven tarde.

## Problema que resuelve
Hoy el mostrador lleva todo a mano: para saber si hay copias de una película, quién la tiene o si un socio debe algo, hay que revisar fichas una por una. El programa centraliza el catálogo, los socios y los alquileres, encuentra cualquier dato al instante por código o DNI y genera reportes ordenados.

## Tema elegido
**Clase 7: Mapas.** En un mostrador todo se busca por un identificador: la película por su código y el socio por su DNI. Un mapa va directo a esa llave sin recorrer el catálogo completo, y un `TreeMap` da los reportes ordenados sin ordenar a mano.

Cómo se cumple el mínimo técnico:
- `Map<Integer, Pelicula>`: catálogo, código → película.
- Recorridos con `entrySet` para listados y facturación.
- `TreeMap` para reportes ordenados (catálogo alfabético, alquileres por género).

## Funcionalidades planificadas
- [ ] Alta, baja, búsqueda y listado de películas
- [ ] Alta y búsqueda de socios por DNI
- [ ] Alquilar y devolver películas, controlando copias disponibles
- [ ] Recargo por devolución atrasada
- [ ] Facturación de mostrador con detalle y total
- [ ] Reportes: catálogo alfabético, más alquiladas, alquileres por género

## Fuera de alcance
Pagos reales, usuarios con contraseña, base de datos e interfaz gráfica. El programa funciona por consola.

## Tecnologías
Java (versión a definir), aplicación de consola, sin librerías externas.

## Cómo ejecutar
A completar cuando haya código.

## Estructura del repositorio
- `bitacora.md`: avance diario
- `/src`: código
- `/docs`: análisis y diseño
- `/pruebas`: casos de prueba
- `/kanban`: capturas diarias del tablero

## Capturas de pantalla
A completar.

## Pruebas realizadas
A completar (detalle en `/pruebas`).

## Conclusiones
A completar al cierre del proyecto.
