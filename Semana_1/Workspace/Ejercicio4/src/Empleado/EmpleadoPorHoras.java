package Empleado;

public class EmpleadoPorHoras extends Empleado {
    public EmpleadoPorHoras(String nombre, int edad, double salario) {
        super(nombre, edad, salario);
    }

    @Override
    public double calcularBono() {
        int extra = Math.max(0, getEdad() - 25);
        return extra * 100.0;
    }
}

