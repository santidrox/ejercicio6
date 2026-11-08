/** Pruebas de lógica sin abrir ventanas. Ejecutar con la opción -ea. */
public class PruebasDashboard {
    public static void main(String[] args) {
        Dashboard modelo = new Dashboard();
        assert modelo.listarActivos().equals("No hay widgets activos.");
        modelo.cargarInicial();
        for (int id = 1; id <= 10; id++) { assert modelo.buscar(id) != null; }
        modelo.cargarInicial();
        assert modelo.listarActivos().lines().count() == 10;
        assert modelo.buscar("  universidad ").getId() == 4;
        assert modelo.buscar("ausente") == null;
        assert modelo.buscar((String) null) == null;
        assert modelo.buscar(50) == null;
        modelo.ordenarPorMemoria();
        String listado = modelo.listarActivos();
        double anterior = -1;
        for (String fila : listado.split("\n")) {
            int id = Integer.parseInt(fila.substring(1, fila.indexOf(" |")));
            double memoria = modelo.buscar(id).getMemoriaKB();
            assert memoria >= anterior;
            anterior = memoria;
        }
        Widget tareas = modelo.buscar(4);
        assert tareas instanceof Interactivo;
        Interactivo acciones = (Interactivo) tareas;
        assert acciones.interactuar("0").contains("no corresponde");
        assert acciones.interactuar("hola").contains("Escribe");
        assert acciones.interactuar(null).contains("Escribe");
        assert acciones.interactuar("1").startsWith("Tarea completada:");
        assert !tareas.renderizar().contains("Terminar ejercicio de POO");
        acciones.interactuar("1");
        acciones.interactuar("1");
        assert acciones.interactuar("1").contains("No hay tareas");
        AlertaWidget alerta = (AlertaWidget) modelo.buscar(7);
        assert !alerta.isLeida();
        assert alerta.interactuar("borrar").contains("no válida");
        assert !alerta.isLeida();
        alerta.interactuar(" LEER ");
        assert alerta.isLeida();
        assert alerta.interactuar("leer").contains("ya estaba");
        assert !(modelo.buscar(1) instanceof Interactivo);
        assert !(modelo.buscar(9) instanceof Interactivo);
        DashboardController controlador = new DashboardController(modelo, new DashboardView());
        assert controlador.renderizar(modelo.buscar(1)).contains("CLIMA");
        assert controlador.interactuar(alerta, "leer").contains("ya estaba");
        modelo.buscar(1).setActivo(false);
        assert !modelo.listarActivos().contains("Clima Guatemala");
        assert !modelo.renderizarActivos().contains("Clima Guatemala");
        comprobarRechazo(() -> modelo.agregar(modelo.buscar(2)));
        comprobarRechazo(() -> modelo.agregar(null));
        comprobarRechazo(() -> tareas.setNombre("  "));
        comprobarRechazo(() -> tareas.setMemoriaKB(-1));
        comprobarRechazo(() -> tareas.setMemoriaKB(Double.NaN));
        comprobarRechazo(() -> tareas.setMemoriaKB(Double.POSITIVE_INFINITY));
        comprobarRechazo(() -> new ClimaWidget(0, "Inválido", 1, true, "Ciudad", 20));
        Widget nuevo = new Widget(11, "Nuevo tipo", 33, true) {
            public String renderizar() { return "Contenido del nuevo tipo"; }
            public String getConfiguracion() { return toString(); }
        };
        modelo.agregar(nuevo);
        assert modelo.renderizarActivos().contains("Contenido del nuevo tipo");
        Widget mismoId = new RelojWidget(11, "Misma identidad", 33, true, "UTC");
        assert nuevo.equals(mismoId);
        assert nuevo.hashCode() == mismoId.hashCode();
        assert !nuevo.equals(null);
        assert nuevo.compareTo(mismoId) == 0;
    }

    private static void comprobarRechazo(Runnable operacion) {
        boolean rechazada = false;
        try { operacion.run(); }
        catch (IllegalArgumentException ex) { rechazada = true; }
        assert rechazada : "La operación debía rechazar datos inválidos";
    }
}
