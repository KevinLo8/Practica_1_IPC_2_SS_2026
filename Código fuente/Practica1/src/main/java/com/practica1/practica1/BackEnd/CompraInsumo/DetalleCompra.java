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

    private final String CODIGO;
    private final String CODIGO_COMPRA;
    private final String CODIGO_INSUMO;
    private final String NOMBRE_INSUMO;
    private final double CANTIDAD;
    private final double COSTO;

    public DetalleCompra(String codigo, String codigoCompra, String codigoInsumo, String nombreInsumo, double cantidad, double costo) throws FormatoDatosException {
        revisarDatos(cantidad, costo);
        CODIGO = codigo;
        CODIGO_COMPRA = codigoCompra;
        CODIGO_INSUMO = codigoInsumo;
        NOMBRE_INSUMO = nombreInsumo;
        CANTIDAD = cantidad;
        COSTO = costo;
    }

    public String getCodigo() {
        return CODIGO;
    }

    public String getCodigoCompra() {
        return CODIGO_COMPRA;
    }
    
    public String getCodigoInsumo() {
        return CODIGO_INSUMO;
    }

    public String getNombreInsumo() {
        return NOMBRE_INSUMO;
    }

    public double getCantidad() {
        return CANTIDAD;
    }

    public double getCosto() {
        return COSTO;
    }
    
        private void revisarDatos(double cantidad, double costo) throws FormatoDatosException {
        if (cantidad <= 0) {
            throw new FormatoDatosException("Ingrese una cantidad mayor a 0.");
        }
        if (costo <= 0) {
            throw new FormatoDatosException("Ingrese un costo mayor a 0.");
        }
    }

}
