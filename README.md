# Dashboard modular — Ejercicio 6

Prototipo Java con interfaz gráfica Swing, MVC, herencia y polimorfismo vía interfaces.

## Requisitos

Java 11 o superior. Para compilar se necesita el JDK. No requiere librerías externas.

## Ejecutar

Descomprime el ZIP y abre una terminal dentro de la carpeta DashboardModular:

```sh
java -cp bin Principal
```

Los archivos compilados incluidos son compatibles con Java 11 o superior.

Para volver a compilar:

```sh
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Principal
```

En VS Code también puedes abrir la carpeta y ejecutar el método main de src/Principal.java.

## Estructura

- src/: clases e interfaces, cada una en su archivo .java.
- bin/: archivos compilados .class.
- lib/: reservada para dependencias; el programa utiliza solo la biblioteca estándar.
- pruebas/: pruebas de la lógica del programa sin abrir ventanas.
- UML_Dashboard.png: diseño de clases.

No se utiliza una carpeta data porque no hay archivos de persistencia.

## Uso

- Ver panel: muestra el contenido de los widgets activos.
- Listar activos: muestra ID, nombre, memoria y estado.
- Buscar por ID: por ejemplo, 4.
- Buscar por nombre: por ejemplo, Universidad; no distingue mayúsculas.
- Ordenar por memoria: ordena la única lista mediante Comparable, de menor a mayor consumo.
- Interactuar: solicita un ID y después una acción según el widget.
- Salir: termina la aplicación; cerrar el menú también termina.

Ejemplos de interacción:

- ID 4: escribe 1 para completar la primera tarea pendiente. Al completarla se elimina de pendientes y las restantes se renumeran.
- ID 7: escribe leer para marcar la alerta como leída.
- ID 1 o 9: muestra un aviso porque son informativos y no implementan Interactivo.

El catálogo carga diez widgets activos: tres de clima, tres de tareas, dos alertas y dos relojes.
Los valores de clima y memoria son datos de ejemplo, no mediciones reales.
Los relojes consultan la hora actual al abrir nuevamente Ver panel; no utilizan un temporizador.
Los cambios de tareas y alertas duran hasta cerrar el programa. Al reiniciar se carga el catálogo inicial.

## Diseño

Dashboard conserva un único ArrayList<Widget>. No se crean listas separadas por tipo ni copias para filtrar activos.
La lista interna de TareasWidget contiene texto de tareas y no otra colección de widgets.

Widget implementa Renderizable y Comparable<Widget>. TareasWidget y AlertaWidget también implementan Interactivo.
DashboardController recibe interfaces como parámetros para los comportamientos.
Los métodos buscar(int) y buscar(String) aplican sobrecarga.
Widget implementa toString, equals y hashCode; el ID es inmutable.

Para crear un nuevo tipo, se extiende Widget, se implementan sus métodos y se agrega una instancia al catálogo.
El contenedor y el controlador no requieren condiciones para reconocer el nuevo tipo.

No hay while(true) ni mensajes System.out.println. Los break existentes pertenecen al switch del menú.
La vista presenta mensajes mediante Swing.

## Pruebas de lógica

Desde la carpeta del proyecto:

```sh
javac -encoding UTF-8 -cp bin -d bin pruebas/PruebasDashboard.java
java -ea -Djava.awt.headless=true -cp bin PruebasDashboard
```

Si finaliza sin errores, las comprobaciones pasan. Se verifican la carga inicial, las búsquedas, el orden,
las interacciones, los datos inválidos y la incorporación de un nuevo tipo sin modificar el contenedor.

Validación realizada: compilación dirigida a Java 11 y ejecución satisfactoria de estas pruebas sobre Java 17.
Las ventanas no se comprobaron visualmente porque el entorno de validación no dispone de pantalla.

## GitHub

La carpeta src contiene el código que se debe subir al repositorio. La carpeta bin se ignora mediante .gitignore,
porque los archivos compilados se regeneran con javac. El PDF de análisis y diseño se entrega por separado.
