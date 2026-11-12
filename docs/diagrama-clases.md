# Diagrama de Clases

```mermaid
classDiagram
    class MenuPrincipal {
        +main(String[] args)
    }
    class Catalogo {
        -Map<Integer, Pelicula> peliculas
        +agregarPelicula()
        +buscarPorCodigo(int codigo)
        +retirarCopia()
        +devolverCopia()
    }
    class Alquileres {
        -Map<String, Socio> socios
        +alquilar(String dni, int codigo, int dia)
        +devolver(String dni, int codigo, int dia)
    }
    MenuPrincipal --> Catalogo : usa
    MenuPrincipal --> Alquileres : usa
