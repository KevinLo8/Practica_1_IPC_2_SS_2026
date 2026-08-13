/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Insumo;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author kevinl
 */
public class Insumo {

    private final String CODIGO;
    private final String NOMBRE;
    private final String UNIDAD_DE_MEDIDA;
    private double stock;
    private final double STOCK_MINIMO;
    private double costo;

    public Insumo(String codigo, String nombre, String unidadMedida, double stock, double stockMinimo, double costo) throws FormatoDatosException {
        revisarDatos(codigo, nombre, unidadMedida);
        CODIGO = codigo;
        NOMBRE = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        UNIDAD_DE_MEDIDA = unidadMedida.toUpperCase();
        this.stock = stock;
        STOCK_MINIMO = stockMinimo;
        this.costo = costo;
    }

    public String getCodigo() {
        return CODIGO;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public String getUnidadMedida() {
        return UNIDAD_DE_MEDIDA;
    }

    public double getStock() {
        return stock;
    }

    public double getStockMinimo() {
        return STOCK_MINIMO;
    }

    public double getCosto() {
        return costo;
    }

    private void revisarDatos(String codigo, String nombre, String unidadMedida) throws FormatoDatosException {
        if (!codigo.matches("^I\\d{4}$")) {
            throw new FormatoDatosException("Formato de codigo incorrecto.");
        }
        if (nombre == null | unidadMedida == null) {
            throw new FormatoDatosException("Llene todos los espacios necesarios.");
        }
        if (nombre.length() > 100) {
            throw new FormatoDatosException("Ingrese un nombre de tamaño menor de 100.");
        }
        if (unidadMedida.length() > 50) {
            throw new FormatoDatosException("Ingrese una unidad de medida de tamaño menor de 50.");
        }
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
