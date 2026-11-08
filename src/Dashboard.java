import java.util.ArrayList;
import java.util.Collections;

/** Modelo: conserva una sola colección polimórfica de widgets. */
public class Dashboard {
    private final ArrayList<Widget> widgets = new ArrayList<>();

    public void cargarInicial() {
        if (!widgets.isEmpty()) { return; }
        agregar(new ClimaWidget(1, "Clima Guatemala", 128, true, "Guatemala", 23));
        agregar(new ClimaWidget(2, "Clima Antigua", 110, true, "Antigua Guatemala", 21));
        agregar(new ClimaWidget(3, "Clima Xela", 115, true, "Quetzaltenango", 16));
        agregar(new TareasWidget(4, "Universidad", 210, true,
                "Terminar ejercicio de POO", "Repasar interfaces", "Subir código a GitHub"));
        agregar(new TareasWidget(5, "Trabajo", 185, true,
                "Revisar el informe", "Preparar reunión"));
        agregar(new TareasWidget(6, "Personal", 160, true,
                "Ordenar archivos", "Revisar correo"));
        agregar(new AlertaWidget(7, "Entrega pendiente", 80, true,
                "Recuerda entregar el ejercicio 6."));
        agregar(new AlertaWidget(8, "Aviso del sistema", 65, true,
                "Hay una actualización disponible."));
        agregar(new RelojWidget(9, "Hora Guatemala", 45, true, "America/Guatemala"));
        agregar(new RelojWidget(10, "Hora Madrid", 48, true, "Europe/Madrid"));
    }

    public void agregar(Widget widget) {
        if (widget == null) { throw new IllegalArgumentException("El widget no puede ser nulo."); }
        if (buscar(widget.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un widget con ese ID.");
        }
        widgets.add(widget);
    }

    public Widget buscar(int id) {
        for (Widget widget : widgets) {
            if (widget.getId() == id) { return widget; }
        }
        return null;
    }

    // Sobrecarga: el mismo nombre de método acepta otro tipo de parámetro.
    public Widget buscar(String nombre) {
        if (nombre == null) { return null; }
        for (Widget widget : widgets) {
            if (widget.getNombre().equalsIgnoreCase(nombre.trim())) { return widget; }
        }
        return null;
    }

    public String listarActivos() {
        StringBuilder contenido = new StringBuilder();
        for (Widget widget : widgets) {
            if (widget.isActivo()) { contenido.append(widget).append('\n'); }
        }
        return contenido.length() == 0 ? "No hay widgets activos." : contenido.toString();
    }

    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }

    public String renderizarActivos() {
        StringBuilder contenido = new StringBuilder();
        for (Widget widget : widgets) {
            if (widget.isActivo()) {
                Renderizable elemento = widget;
                contenido.append(elemento.renderizar()).append("\n\n─────────────────────────\n\n");
            }
        }
        return contenido.length() == 0 ? "No hay widgets activos." : contenido.toString();
    }
}
