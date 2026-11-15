# Contrato de Módulo: Menú, Reportes e Integración

**Responsable:** Santiago Sanchez

## Clases responsables
- `MenuPrincipal.java`: contiene el `main`, el menú por consola y los datos de prueba iniciales. Es la única clase que lee del teclado.
- `Reportes.java`: arma y muestra los tres reportes ordenados a partir de los datos del catálogo y de los alquileres.

Ambas clases se encuentran en `/src`, dentro del paquete por defecto.

## Cómo se conectan los módulos
`MenuPrincipal` crea los objetos una sola vez al iniciar y se los pasa a quien los necesita:

    Catalogo catalogo = new Catalogo();
    Alquileres alquileres = new Alquileres(catalogo);
    Reportes reportes = new Reportes(catalogo, alquileres);

Ningún módulo accede a los mapas de otro: `MenuPrincipal` y `Reportes` trabajan solo con los métodos públicos de `Catalogo` y `Alquileres`.

## MenuPrincipal

| Opción | Hace | Métodos que usa |
|---|---|---|
| 1. Agregar película | Pide código, título, género, año y copias | `catalogo.agregarPelicula` |
| 2. Buscar película | Acepta un código o parte del título | `catalogo.buscarPorCodigo`, `catalogo.buscarPorTitulo` |
| 3. Eliminar película | Rechaza si no existe o tiene copias alquiladas | `catalogo.eliminarPelicula` |
| 4. Listar catálogo | Muestra todas las películas | `catalogo.getPeliculas` |
| 5. Registrar socio | Pide DNI y nombre | `alquileres.agregarSocio` |
| 6. Buscar socio | Muestra datos, deuda y películas en su poder | `alquileres.buscarSocio`, `alquileres.alquileresDe` |
| 7. Alquilar película | Pide DNI, código y día; muestra el vencimiento o el motivo del rechazo | `alquileres.alquilar` |
| 8. Devolver película | Pide DNI, código y día; informa el recargo si hubo atraso | `alquileres.devolver` |
| 9. Pagar deuda | Salda la deuda del socio | `alquileres.pagarDeuda` |
| 10. Reportes | Muestra los tres reportes | `reportes.mostrarTodos` |
| 0. Salir | Termina el programa | |

Reglas de la interfaz:
- Cada operación vuelve al menú hasta que se elige Salir, con un `do-while`.
- Todas las lecturas se hacen con `nextLine()` y los números se convierten con `Integer.parseInt` dentro de un `try-catch`: si se ingresa texto donde va un número, se avisa y se vuelve a pedir, sin cortar el programa. Así se evita además el problema del Enter pendiente de `nextInt()`.
- Al iniciar se cargan 5 películas y 3 socios de prueba, para poder mostrar el sistema sin cargar datos a mano.
- Las fechas se manejan con un número de día que el usuario ingresa en cada alquiler y devolución.

## Reportes

Se crea con `new Reportes(catalogo, alquileres)`. Imprime por consola.

| Método | Muestra | Estructura |
|---|---|---|
| `catalogoAlfabetico()` | El catálogo ordenado por título, sin distinguir mayúsculas | `TreeMap<String, Pelicula>`; la llave lleva el código para que dos títulos iguales no se pisen |
| `masAlquiladas()` | Las películas de más a menos alquiladas | `TreeMap<Integer, List<String>>` con `Collections.reverseOrder()`; la llave es la cantidad y el valor la lista de títulos, así los empates no se pierden |
| `alquileresPorGenero()` | La cantidad de alquileres acumulados por género, en orden alfabético | `TreeMap<String, Integer>` acumulando con `getOrDefault` |
| `mostrarTodos()` | Los tres reportes seguidos | |

Los tres reportes se recorren con `entrySet()`. Si no hay películas o no hay alquileres, muestran un aviso en lugar de fallar. Una película dada de baja sigue apareciendo en el ranking como "Pelicula dada de baja", porque el contador guarda el código.

## Datos que recibe de los otros módulos
- De `Catalogo`: `getPeliculas()`, que devuelve el mapa en modo de solo lectura, y `buscarPorCodigo`.
- De `Alquileres`: `getVecesAlquilada()`, el mapa de código → cantidad de alquileres.

## Estado de implementación
- Listo: `MenuPrincipal` conectado a las clases reales y `Reportes` con los tres reportes.
- Pruebas: `pruebas/PruebaReportes.java` y `pruebas/pruebas-menu-reportes.md`.
