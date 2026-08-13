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
public enum Jornada {
    MATUTINA,
    VESPERTINA,
    NOCTURNA;
    
    public static Jornada retornarJornada(String entrante) throws FormatoDatosException {
        switch (entrante.toUpperCase()) {
            case "MATUTINA" -> {
                return Jornada.MATUTINA;
            }
            case "VESPERTINA" -> {
                return Jornada.VESPERTINA;
            }
            case "NOCTURNA" -> {
                return Jornada.NOCTURNA;
            }
            default -> throw new FormatoDatosException("Selecione una jornada laboral valida.");
        }
    }
}
