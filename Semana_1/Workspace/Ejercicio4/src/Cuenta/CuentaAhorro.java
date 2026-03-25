package Cuenta;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;

    public CuentaAhorro(String titular, double cantidadInicial, double tasaInteres) {
        super(titular, cantidadInicial);
        this.tasaInteres = Math.max(0, tasaInteres);
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = Math.max(0, tasaInteres);
    }

    public void calcularInteres() {
        double interes = getCantidad() * tasaInteres / 100.0;
        System.out.println("Interés generado ("
                + tasaInteres + "%): " + interes);
    }
}