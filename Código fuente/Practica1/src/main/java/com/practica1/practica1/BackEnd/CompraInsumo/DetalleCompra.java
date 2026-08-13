/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.CompraInsumo;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author Kevin
 */
public class DetalleCompra {

    private final String codigo;
    private final String codigoCompra;
    private final String codigoInsumo;
    private final String nombreInsumo;
    private final int cantidad;
    private final double costo;

    public DetalleCompra(String codigo, String codigoCompra, String codigoInsumo, String nombreInsumo, int cantidad, double costo) throws FormatoDatosException {
        revisarDatos(cantidad, costo);
        this.codigo = codigo;
        this.codigoCompra = codigoCompra;
        this.codigoInsumo = codigoInsumo;
        this.nombreInsumo = nombreInsumo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }
    
    public String getCodigoInsumo() {
        return codigoInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCosto() {
        return costo;
    }
    
        private void revisarDatos(int cantidad, double costo) throws FormatoDatosException {
        if (cantidad <= 0) {
            throw new FormatoDatosException("Ingrese una cantidad mayor a 0.");
        }
        if (costo <= 0) {
            throw new FormatoDatosException("Ingrese un costo mayor a 0.");
        }
    }

}
