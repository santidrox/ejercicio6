import java.util.Locale;

/** Datos comunes, identidad y orden natural de todos los widgets. */
public abstract class Widget implements Renderizable, Comparable<Widget> {
    private final int id;
    private String nombre;
    private double memoriaKB;
    private boolean activo;

    protected Widget(int id, String nombre, double memoriaKB, boolean activo) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }
        this.id = id;
        setNombre(nombre);
        setMemoriaKB(memoriaKB);
        this.activo = activo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getMemoriaKB() { return memoriaKB; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setMemoriaKB(double memoriaKB) {
        if (!Double.isFinite(memoriaKB) || memoriaKB < 0) {
            throw new IllegalArgumentException("La memoria debe ser un número válido no negativo.");
        }
        this.memoriaKB = memoriaKB;
    }

    @Override
    public int compareTo(Widget otro) {
        int resultado = Double.compare(memoriaKB, otro.memoriaKB);
        return resultado != 0 ? resultado : Integer.compare(id, otro.id);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "#%d | %s | %.1f KB | %s", id, nombre,
                memoriaKB, activo ? "Activo" : "Inactivo");
    }

    @Override
    public final boolean equals(Object obj) {
        return obj instanceof Widget && id == ((Widget) obj).id;
    }

    @Override
    public final int hashCode() { return Integer.hashCode(id); }

    @Override
    public abstract String renderizar();
    public abstract String getConfiguracion();
}
