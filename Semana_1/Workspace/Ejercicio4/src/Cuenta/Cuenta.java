package Cuenta;

public class Cuenta {
    private String titular;
    private double cantidad;

    public Cuenta(String titular, double cantidadInicial) {
        this.titular = titular;
        this.cantidad = Math.max(0, cantidadInicial);
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = Math.max(0, cantidad);
    }

    // Método para mostrar información
    public void mostrarInfo() {
        System.out.println("Titular: " + titular);
        System.out.println("Cantidad actual: " + cantidad);
    }
}