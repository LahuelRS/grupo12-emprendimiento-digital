# Requerimientos: Catálogo de Películas

Responsable: Sacco (@JuanDSacco)

## Historias de usuario
1. Como empleado del mostrador, quiero registrar una película nueva con su código, título, género, año y cantidad de copias, para sumarla al catálogo.
2. Como empleado, quiero buscar una película por su código, para ver sus datos y si quedan copias.
3. Como empleado, quiero buscar películas por una parte del título, para encontrarlas cuando el cliente no sabe el código.
4. Como empleado, quiero eliminar una película del catálogo, para dar de baja los títulos que ya no se alquilan.
5. Como empleado, quiero listar todo el catálogo, para ver qué títulos hay y cuántas copias tiene cada uno.

## Datos
- Película: código (identificador único), título, género, año, copias totales, copias disponibles.

## Reglas del negocio
- No puede haber dos películas con el mismo código.
- El título no puede estar vacío y las copias no pueden ser negativas.
- Las copias disponibles nunca superan las copias totales.
- No se puede eliminar una película que tiene copias alquiladas.

## Casos de error
- Registrar una película con un código que ya existe.
- Buscar o eliminar un código que no existe.
- Retirar una copia de una película sin copias disponibles.
- Devolver una copia cuando ya están todas en el local.

## Qué ofrece a los otros módulos
- Buscar una película por código.
- Informar si hay copias disponibles, retirar una copia y devolverla.
- Entregar el catálogo completo para los reportes.

## Estructuras previstas (tema: Mapas)
- Map<Integer, Pelicula>: código → película.
