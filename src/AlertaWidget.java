/** Notificación interactiva que puede marcarse como leída. */
public class AlertaWidget extends Widget implements Interactivo {
    private final String mensaje;
    private boolean leida;

    public AlertaWidget(int id, String nombre, double memoriaKB, boolean activo, String mensaje) {
        super(id, nombre, memoriaKB, activo);
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }
        this.mensaje = mensaje.trim();
    }

    public String getMensaje() { return mensaje; }
    public boolean isLeida() { return leida; }

    @Override
    public String renderizar() {
        return "ALERTA · " + getNombre() + "\n" + mensaje + "\nEstado: "
                + (leida ? "Leída" : "Sin leer");
    }

    @Override
    public String getConfiguracion() {
        return toString() + "\nTipo: alerta\nMensaje: " + mensaje + "\nLeída: "
                + (leida ? "Sí" : "No") + "\nAcción: escribe leer para marcarla como leída.";
    }

    @Override
    public String interactuar(String accion) {
        if (accion == null || !"leer".equalsIgnoreCase(accion.trim())) {
            return "Acción no válida. Escribe leer.";
        }
        if (leida) { return "Esta alerta ya estaba marcada como leída."; }
        leida = true;
        return "La alerta fue marcada como leída.";
    }
}
