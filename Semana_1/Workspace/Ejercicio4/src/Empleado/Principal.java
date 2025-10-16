package Empleado;

public class Principal {
	public static void main(String[] args) {
        Empleado emp1 = new EmpleadoTiempoCompleto("Ana Pérez", 30, 2000.0);
        Empleado emp2 = new EmpleadoPorHoras("Luis Gómez", 28, 1200.0);

        System.out.println("=== Empleado Tiempo Completo ===");
        emp1.mostrarInfo();
        System.out.println("Bono: " + emp1.calcularBono());

        System.out.println("\n=== Empleado Por Horas ===");
        emp2.mostrarInfo();
        System.out.println("Bono: " + emp2.calcularBono());
    }
}
