import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Vista gráfica: recibe entradas y presenta resultados sin imprimir en consola. */
public class DashboardView {
    private JLabel encabezado;

    public void mostrarMenu() {
        encabezado = new JLabel("<html><h2>Dashboard modular</h2>"
                + "Clima, tareas, alertas y relojes.<br>Selecciona una opción para continuar.<br><br></html>");
    }

    public int leerOpcion() {
        if (encabezado == null) { mostrarMenu(); }
        String[] opciones = {"Ver panel", "Listar activos", "Buscar por ID", "Buscar por nombre",
                "Ordenar por memoria", "Interactuar", "Salir"};
        return JOptionPane.showOptionDialog(null, encabezado, "Dashboard modular",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
    }

    public String leerTexto() {
        return JOptionPane.showInputDialog(null, "Ingresa el dato solicitado (Cancelar para regresar):",
                "Dashboard modular", JOptionPane.QUESTION_MESSAGE);
    }

    public void mostrarMensaje(String texto) {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        area.setBackground(new Color(246, 249, 247));
        area.setForeground(new Color(35, 55, 46));
        area.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setCaretPosition(0);
        JScrollPane panel = new JScrollPane(area);
        panel.setPreferredSize(new Dimension(710, 460));
        JOptionPane.showMessageDialog(null, panel, "Dashboard modular", JOptionPane.PLAIN_MESSAGE);
    }
}
