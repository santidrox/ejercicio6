/** Controlador: procesa el menú y coordina modelo, vista e interfaces. */
public class DashboardController {
    private final Dashboard modelo;
    private final DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        modelo.cargarInicial();
        boolean ejecutando = true;
        while (ejecutando) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            switch (opcion) {
                case 0:
                    vista.mostrarMensaje(modelo.renderizarActivos());
                    break;
                case 1:
                    vista.mostrarMensaje(modelo.listarActivos());
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    modelo.ordenarPorMemoria();
                    vista.mostrarMensaje("Widgets activos ordenados de menor a mayor memoria:\n\n"
                            + modelo.listarActivos());
                    break;
                case 5:
                    ejecutarInteraccion();
                    break;
                default:
                    ejecutando = false;
            }
        }
    }

    private void buscarPorId() {
        vista.mostrarMensaje("Escribe el ID del widget que deseas consultar.");
        String dato = vista.leerTexto();
        if (dato == null) { return; }
        try {
            mostrarResultado(modelo.buscar(Integer.parseInt(dato.trim())));
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("El ID debe ser un número entero.");
        }
    }

    private void buscarPorNombre() {
        vista.mostrarMensaje("Escribe el nombre completo del widget. Por ejemplo: Universidad.");
        String dato = vista.leerTexto();
        if (dato != null) { mostrarResultado(modelo.buscar(dato)); }
    }

    private void mostrarResultado(Widget widget) {
        vista.mostrarMensaje(widget == null ? "No se encontró un widget con ese dato."
                : widget.getConfiguracion());
    }

    private void ejecutarInteraccion() {
        vista.mostrarMensaje("Escribe el ID del widget con el que deseas interactuar.\n\n"
                + modelo.listarActivos());
        String dato = vista.leerTexto();
        if (dato == null) { return; }
        try {
            Widget widget = modelo.buscar(Integer.parseInt(dato.trim()));
            if (widget == null) {
                vista.mostrarMensaje("No se encontró el widget.");
            } else if (!widget.isActivo()) {
                vista.mostrarMensaje("El widget está inactivo.");
            } else if (widget instanceof Interactivo) {
                vista.mostrarMensaje(widget.getConfiguracion());
                String accion = vista.leerTexto();
                if (accion != null) {
                    vista.mostrarMensaje(interactuar((Interactivo) widget, accion)
                            + "\n\n" + renderizar(widget));
                }
            } else {
                vista.mostrarMensaje("Este widget es informativo y no permite acciones.");
            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("El ID debe ser un número entero.");
        }
    }

    public String renderizar(Renderizable item) {
        return item.renderizar();
    }

    public String interactuar(Interactivo item, String accion) {
        return item.interactuar(accion);
    }
}
