# Contrato entre módulos: acuerdos generales

Cada módulo guarda sus datos en mapas privados: nadie accede al mapa de otro, solo a sus métodos públicos.
Los métodos de cada módulo están en su propio archivo:

- [Catálogo (Sacco)](catalogo.md): Pelicula y Catalogo
- [Socios y alquileres (Roman)](alquileres.md): Socio y Alquileres
- [Menú, reportes e integración (Sanchez)](menu.md): Reportes y MenuPrincipal

## Acuerdos
- Todas las clases van en /src, en el paquete por defecto (sin línea package).
- Código de película: int. DNI del socio: String.
- Solo MenuPrincipal lee del teclado. Los demás módulos reciben los datos por parámetro y devuelven un resultado.
- Las fechas se manejan con un número de día (int): el menú pide el día de la operación y lo pasa como parámetro.
- Reglas: plazo de alquiler 3 días, recargo de 1500 pesos por día de atraso, máximo 3 películas por socio, un socio con deuda no puede alquilar.

## Cómo se conectan
MenuPrincipal crea los objetos una sola vez y se los pasa a quien los necesita:

    Catalogo catalogo = new Catalogo();
    Alquileres alquileres = new Alquileres(catalogo);
    Reportes reportes = new Reportes(catalogo, alquileres);

- Para alquilar, el menú llama a alquileres.alquilar(dni, codigo, dia). Adentro, Alquileres usa catalogo.buscarPorCodigo y catalogo.retirarCopia.
- Para devolver, el menú llama a alquileres.devolver(dni, codigo, dia), que usa catalogo.devolverCopia y calcula el recargo.
- Los reportes leen catalogo.getPeliculas() y alquileres.getVecesAlquilada().
