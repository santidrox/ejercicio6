/** Visor informativo con datos de clima de ejemplo, sin conexión a internet. */
public class ClimaWidget extends Widget {
    private final String ciudad;
    private final double temperatura;

    public ClimaWidget(int id, String nombre, double memoriaKB, boolean activo,
                       String ciudad, double temperatura) {
        super(id, nombre, memoriaKB, activo);
        if (ciudad == null || ciudad.trim().isEmpty() || !Double.isFinite(temperatura)) {
            throw new IllegalArgumentException("La ciudad o temperatura no son válidas.");
        }
        this.ciudad = ciudad.trim();
        this.temperatura = temperatura;
    }

    public String getCiudad() { return ciudad; }
    public double getTemperatura() { return temperatura; }

    @Override
    public String renderizar() {
        return "CLIMA · " + getNombre() + "\n" + ciudad + ": " + temperatura + " °C";
    }

    @Override
    public String getConfiguracion() {
        return toString() + "\nTipo: clima\nCiudad: " + ciudad
                + "\nTemperatura de ejemplo: " + temperatura + " °C";
    }
}
