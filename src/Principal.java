import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Punto de entrada del ejercicio 6: polimorfismo mediante interfaces. */
public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {

            }
            Dashboard modelo = new Dashboard();
            DashboardView vista = new DashboardView();
            new DashboardController(modelo, vista).iniciar();
        });
    }
}
