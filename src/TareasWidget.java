import java.util.ArrayList;
import java.util.List;

public class TareasWidget extends Widget implements Interactivo {
    private final List<String> tareas = new ArrayList<>();

    public TareasWidget(int id, String nombre, double memoriaKB, boolean activo,
                        String... pendientes) {
        super(id, nombre, memoriaKB, activo);
        for (String pendiente : pendientes) {
            if (pendiente == null || pendiente.trim().isEmpty()) {
                throw new IllegalArgumentException("Una tarea no puede estar vacía.");
            }
            tareas.add(pendiente.trim());
        }
    }

    @Override
    public String renderizar() {
        StringBuilder contenido = new StringBuilder("TAREAS · " + getNombre() + "\n");
        if (tareas.isEmpty()) {
            contenido.append("No hay tareas pendientes.");
        }
        for (int i = 0; i < tareas.size(); i++) {
            contenido.append(i + 1).append(". ").append(tareas.get(i)).append('\n');
        }
        return contenido.toString().trim();
    }

    @Override
    public String getConfiguracion() {
        return toString() + "\nTipo: tareas\nPendientes: " + tareas.size()
                + "\nAcción: escribe el número de la tarea para completarla.\n\n" + renderizar();
    }

    @Override
    public String interactuar(String accion) {
        if (tareas.isEmpty()) { return "No hay tareas pendientes para completar."; }
        try {
            int indice = Integer.parseInt(accion == null ? "" : accion.trim()) - 1;
            if (indice < 0 || indice >= tareas.size()) {
                return "El número no corresponde a una tarea pendiente.";
            }
            return "Tarea completada: " + tareas.remove(indice);
        } catch (NumberFormatException ex) {
            return "Escribe el número de una tarea pendiente.";
        }
    }
}
