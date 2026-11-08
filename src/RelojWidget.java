import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Visor de hora; consulta la hora actual cada vez que se renderiza. */
public class RelojWidget extends Widget {
    private final String zonaHoraria;

    public RelojWidget(int id, String nombre, double memoriaKB, boolean activo, String zonaHoraria) {
        super(id, nombre, memoriaKB, activo);
        this.zonaHoraria = ZoneId.of(zonaHoraria).getId();
    }

    public String getZonaHoraria() { return zonaHoraria; }

    @Override
    public String renderizar() {
        String hora = ZonedDateTime.now(ZoneId.of(zonaHoraria))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return "RELOJ · " + getNombre() + "\n" + zonaHoraria + "\n" + hora;
    }

    @Override
    public String getConfiguracion() {
        return toString() + "\nTipo: reloj\nZona horaria: " + zonaHoraria
                + "\nLa hora se actualiza al mostrar el panel.";
    }
}
