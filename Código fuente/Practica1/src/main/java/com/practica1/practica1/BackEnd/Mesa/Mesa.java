/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Mesa;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;

/**
 *
 * @author kevinl
 */
public class Mesa {
    
    private final int NUMERO;
    private final int CAPACIDAD;
    private final Estado ESTADO;

    public Mesa(int Numero, int capacidad, String estado) throws FormatoDatosException {
        NUMERO = Numero;
        CAPACIDAD = capacidad;
        ESTADO = Estado.retornarEstado(estado);
    }

    public int getNumero() {
        return NUMERO;
    }

    public int getCapacidad() {
        return CAPACIDAD;
    }

    public Estado getEstado() {
        return ESTADO;
    }
    
    
}
