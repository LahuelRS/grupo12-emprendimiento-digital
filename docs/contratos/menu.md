# Contrato de Módulo: Menú, Reportes e Integración

**Responsable:** Santiago Sanchez

## Cómo funciona mi módulo
- **`MenuPrincipal.java`**: Es la clase principal que contiene el `main`. Maneja el bucle con `do-while`, la lectura por consola con `Scanner` y el control de excepciones con `try-catch` para evitar caídas si ingresan letras en vez de números.
- **Integración con otros módulos**:
  - Llama a los métodos de `Catalogo` (`buscarPorCodigo`, `retirarCopia`, etc.) pasándoles los parámetros necesarios.
  - Interactúa con `Alquileres` para procesar préstamos, devoluciones y cálculo de multas de $1500 por día de atraso.
  - Utiliza `TreeMap` y recorridos con `entrySet()` para armar y mostrar los reportes ordenados (alfabético y por género).
