# Pruebas del módulo Catálogo

**Responsable:** Juan Sacco  
**Archivo de pruebas:** `PruebaCatalogo.java`  
**Clases evaluadas:** `Catalogo.java` y `Pelicula.java`

## Objetivo

Verificar el correcto funcionamiento de las operaciones principales del catálogo de películas del sistema Rebobinar: registro, búsqueda, retiro y devolución de copias, eliminación y consulta de películas.

## Casos de prueba

| N.º | Caso de prueba | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| 1 | Registrar Matrix (101) | `true` | `true` | Correcto |
| 2 | Registrar Titanic (102) | `true` | `true` | Correcto |
| 3 | Registrar código duplicado (101) | `false` | `false` | Correcto |
| 4 | Buscar película por código (101) | Matrix | Matrix | Correcto |
| 5 | Buscar por título parcial ("mat") | Matrix | Matrix | Correcto |
| 6 | Retirar una copia de Matrix | `true` | `true` | Correcto |
| 7 | Consultar copias disponibles | 2 copias | 2 copias | Correcto |
| 8 | Eliminar Matrix con una copia alquilada | `false` | `false` | Correcto |
| 9 | Devolver una copia de Matrix | `true` | `true` | Correcto |
| 10 | Eliminar Matrix después de devolverla | `true` | `true` | Correcto |
| 11 | Consultar cantidad de películas restantes | 1 película | 1 película | Correcto |

## Conclusión

Las pruebas funcionales del módulo Catálogo permiten verificar las operaciones principales de registro, búsqueda, administración de copias y eliminación de películas. Los resultados obtenidos coinciden con los esperados, validando el funcionamiento de las funcionalidades implementadas.
