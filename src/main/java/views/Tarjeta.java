/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jesus
 */
public class Tarjeta {
    
    private int numTarjeta;
    private int cvv;
    private LocalDate fechaVencimiento;
    private double Saldo;

    public Tarjeta(int numTarjeta, int cvv, LocalDate fechaVencimiento, double Saldo) {
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.Saldo = Saldo;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.numTarjeta;
        hash = 31 * hash + this.cvv;
        hash = 31 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.Saldo) ^ (Double.doubleToLongBits(this.Saldo) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarjeta other = (Tarjeta) obj;
        if (this.numTarjeta != other.numTarjeta) {
            return false;
        }
        if (this.cvv != other.cvv) {
            return false;
        }
        if (Double.doubleToLongBits(this.Saldo) != Double.doubleToLongBits(other.Saldo)) {
            return false;
        }
        return Objects.equals(this.fechaVencimiento, other.fechaVencimiento);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarjeta{");
        sb.append("numTarjeta=").append(numTarjeta);
        sb.append(", cvv=").append(cvv);
        sb.append(", fechaVencimiento=").append(fechaVencimiento);
        sb.append(", Saldo=").append(Saldo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
