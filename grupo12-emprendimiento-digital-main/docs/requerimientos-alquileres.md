# Requerimientos: socios y alquileres

Responsable: Lahuel Roman (@LahuelRS)

## Historias de usuario
1. Como empleado del mostrador, quiero registrar un socio nuevo con su DNI y nombre, para poder alquilarle películas.
2. Como empleado, quiero buscar un socio por su DNI, para ver sus datos y qué películas tiene en su casa.
3. Como empleado, quiero registrar el alquiler de una película a un socio, para saber quién la tiene y descontar una copia disponible.
4. Como empleado, quiero registrar la devolución de una película, para que la copia vuelva a estar disponible y calcular el recargo si se pasó del plazo.
5. Como dueño, quiero saber cuántas veces se alquiló cada película, para decidir qué títulos comprar.

## Datos
- **Socio:** DNI (identificador único), nombre, deuda acumulada.
- **Alquiler:** DNI del socio, código de la película, día de retiro.

## Reglas del negocio
- Plazo de alquiler: 3 días.
- Recargo: $1500 por cada día de atraso.
- Máximo de películas por socio al mismo tiempo: 3.
- Un socio con deuda no puede alquilar hasta pagarla.

## Casos de error
- Registrar un socio con un DNI que ya existe.
- Alquilar a un socio que no existe.
- Alquilar una película que no existe o que no tiene copias disponibles.
- Alquilar cuando el socio ya llegó al máximo o tiene deuda.
- Devolver una película que ese socio no tiene alquilada.

## Qué necesito del catálogo
- Buscar una película por su código.
- Saber si tiene copias disponibles, retirar una copia y devolverla.

## Estructuras previstas (tema: Mapas)
- `Map<String, Socio>`: DNI → socio.
- `Map<String, List<Integer>>`: DNI → códigos de las películas que tiene alquiladas.
- `Map<Integer, Integer>`: código → cantidad de veces alquilada.
