/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.practica1.practica1.BackEnd.Mesa;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author kevinl
 */
public enum Estado {
    LIBRE,
    OCUPADO;
    
        public static Estado retornarEstado(String entrante) throws FormatoDatosException {
        switch (entrante.toUpperCase()) {
            case "LIBRE" -> {
                return LIBRE;
            }
            case "OCUPADO" -> {
                return OCUPADO;
            }
            default ->
                throw new FormatoDatosException("Seleccione un rol de emplado valido.");
        }
    }

}
