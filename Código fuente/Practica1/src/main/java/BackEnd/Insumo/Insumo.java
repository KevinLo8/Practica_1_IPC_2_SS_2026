/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.Insumo;

import BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author kevinl
 */
public class Insumo {

    public Insumo(String codigo, String nombre, String unidadMedida, int stock, int stockMinimo, double costo) throws FormatoDatosException {
        revisarDatos(codigo, nombre, unidadMedida);
        this.codigo = codigo;
        this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        this.unidadMedida = unidadMedida.toUpperCase();
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.costo = costo;
    }

    
    
    private String codigo;
    private String nombre;
    private String unidadMedida;
    private int stock;
    private int stockMinimo;
    private double costo;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
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
}
