/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Producto;

import com.practica1.practica1.BackEnd.Exceptions.*;
import com.practica1.practica1.BackEnd.Receta.Receta;

/**
 *
 * @author kevinl
 */
public class Producto {

    private final String CODIGO;
    private String nombre;
    private Categoria categoria;
    private double precio;
    //private Foto foto;
    private Receta[] recetario = new Receta[0];

    public Producto(String codigo) throws FormatoDatosException {
        CODIGO = codigo;
        nombre = "";
        categoria = Categoria.COMIDA;
        precio = 0.00;
    }

    public Producto(String codigo, String nombre, String categoria, double precio) throws FormatoDatosException {
        CODIGO = codigo;
        this.nombre = nombre;
        this.categoria = Categoria.retornarCategoria(categoria);
        this.precio = precio;
    }

    public String getCodigo() {
        return CODIGO;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoriaString) throws FormatoDatosException {
        categoria = Categoria.retornarCategoria(categoriaString);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setRecetario(Receta[] recetario) {
        this.recetario = recetario;
    }
    
    public void agregarInsumo(Receta receta) throws DataExistenteException {

        chequerInsumo(receta);

        int index = recetario.length;
        Receta[] recetarioTemp = new Receta[index + 1];

        System.arraycopy(recetario, 0, recetarioTemp, 0, index);

        recetarioTemp[index] = receta;
        recetario = recetarioTemp;

    }

    private void chequerInsumo(Receta receta) throws DataExistenteException {
        int index = recetario.length;
        for (int i = 0; i < index; i++) {
            if (recetario[i].getCodigoInsumo().equals(receta.getCodigoInsumo())) {
                throw new DataExistenteException("Seleccione un insumo que no este agregado.");
            }
        }
    }

    public void eliminarInsumo(int posicion) {

        int index = recetario.length;
        Receta[] recetarioTemp = new Receta[index - 1];

        for (int i = 0, j = 0; i < index; i++) {
            if (i != posicion) {
                recetarioTemp[j] = recetario[i];
                j++;
            }
        }
        
        recetario = recetarioTemp;

    }

    public Receta[] getReceta() {
        return recetario;
    }

}
