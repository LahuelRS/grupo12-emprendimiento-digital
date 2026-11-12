# Requerimientos: Menú, Reportes e Integración

**Responsable:** Santiago Sanchez

## Historias de usuario
1. Como empleado, quiero un menú por consola con opciones claras para operar el videoclub sin tocar el código.
2. Como empleado, quiero que si ingreso mal un dato o una opción inválida, el sistema me avise y no se rompa.
3. Como dueño, quiero un listado del catálogo ordenado alfabéticamente para revisar las películas.
4. Como dueño, quiero ver las películas más alquiladas para saber cuáles funcionan mejor.
5. Como dueño, quiero ver la cantidad de alquileres por género para analizar las preferencias de los socios.

## Opciones del menú principal
1. Agregar película
2. Buscar película
3. Eliminar película
4. Listar catálogo
5. Registrar socio
6. Buscar socio
7. Alquilar película
8. Devolver película (calcula recargo de $1500 por día si hay retraso)
9. Reportes (catálogo alfabético, más alquiladas, alquileres por género)
10. Salir

## Reglas generales
- El menú es el único que usa el `Scanner` para leer del teclado. Los demás módulos reciben los datos por parámetro.
- El programa vuelve al menú después de cada acción hasta que se elija salir.
- Las entradas erróneas o opciones fuera de rango muestran un mensaje de error y el programa continúa ejecutándose.
- Al iniciar el programa se cargan algunos datos de prueba para probar rápido.
