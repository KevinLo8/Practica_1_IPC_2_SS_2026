/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.practica1.practica1.BackEnd.Empleado;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author Kevin
 */
public enum Rol {
    MESERO,
    COCINA,
    BARISTA,
    ADMINISTRADOR;

    public static Rol retornarRol(String entrante) throws FormatoDatosException {
        switch (entrante.toUpperCase()) {
            case "MESERO" -> {
                return Rol.MESERO;
            }
            case "COCINA" -> {
                return Rol.COCINA;
            }
            case "BARISTA" -> {
                return Rol.BARISTA;
            }
            case "ADMINISTRADOR" -> {
                return Rol.ADMINISTRADOR;
            }
            default ->
                throw new FormatoDatosException("Seleccione un rol de emplado valido.");
        }
    }
}
