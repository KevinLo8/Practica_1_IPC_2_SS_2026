/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Receta;

/**
 *
 * @author kevinl
 */
public class Receta {
    
    private final String CODIGO;
    private final String CODIGO_PRODUCTO;
    private final String CODIGO_INSUMO;
    private String nombre;
    private double cantidad;
    private String unidadDeMedida;

    public Receta(String codigo, String codigoProducto, String codigoInsumo, double cantidad) {
        CODIGO = codigo;
        CODIGO_PRODUCTO = codigoProducto;
        CODIGO_INSUMO = codigoInsumo;
        this.cantidad = cantidad;
    }

    public Receta(String codigo, String codigoProducto, String codigoInsumo, String nombre, double cantidad, String unidadDeMedida) {
        CODIGO = codigo;
        CODIGO_PRODUCTO = codigoProducto;
        CODIGO_INSUMO = codigoInsumo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getCodigo() {
        return CODIGO;
    }

    public String getCodigoProducto() {
        return CODIGO_PRODUCTO;
    }

    public String getCodigoInsumo() {
        return CODIGO_INSUMO;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
    
}
