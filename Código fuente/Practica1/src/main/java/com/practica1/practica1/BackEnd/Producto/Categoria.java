/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.practica1.practica1.BackEnd.Producto;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author kevinl
 */
public enum Categoria {
    BEBIDA_CALIENTE,
    BEBIDA_FRIA,
    POSTRE,
    COMIDA;

    public static Categoria retornarCategoria(String entrante) throws FormatoDatosException {
        switch (entrante) {
            case "BEBIDA_CALIENTE" -> {
                return Categoria.BEBIDA_CALIENTE;
            }
            case "BEBIDA_FRIA" -> {
                return Categoria.BEBIDA_FRIA;
            }
            case "POSTRE" -> {
                return Categoria.POSTRE;
            }
            case "COMIDA" -> {
                return Categoria.COMIDA;
            }
            default -> throw new FormatoDatosException("Seleccione una categoria valida.");
        }
    }
}
