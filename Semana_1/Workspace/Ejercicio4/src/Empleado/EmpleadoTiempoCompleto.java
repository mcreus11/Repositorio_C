package Empleado;

public class EmpleadoTiempoCompleto extends Empleado {
    public EmpleadoTiempoCompleto(String nombre, int edad, double salario) {
        super(nombre, edad, salario);
    }

    // 20% del salario
    @Override
    public double calcularBono() {
        return getSalario() * 0.20;
    }
}
