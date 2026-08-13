/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Mesa;

import com.practica1.practica1.BackEnd.DataBase.MesaDB;
import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import com.practica1.practica1.BackEnd.Exceptions.DataExistenteException;

/**
 *
 * @author kevinl
 */
public class OperadorMesa {

    public Object[][] convertirLista(Mesa[] lista) {
        Object[][] datos = new Object[0][3];
        for (Mesa mesa : lista) {
            int index = datos.length;
            Object[][] datosTemp = new Object[index + 1][3];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index][0] = mesa.getNumero();
            datosTemp[index][1] = mesa.getCapacidad();
            datosTemp[index][2] = mesa.getEstado().toString();

            datos = datosTemp;
        }
        return datos;
    }

    public Object[] convertirListaNumero(Mesa[] lista) {
        Object[] datos = new Object[1];
        for (Mesa mesa : lista) {
            int index = datos.length;
            Object[] datosTemp = new Object[index + 1];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index] = mesa.getNumero();

            datos = datosTemp;
        }
        return datos;
    }

    public void revisarNumero(MesaDB database, Mesa mesa) throws DataExistenteException, DataBaseException {
        if (database.revisarNumero(mesa.getNumero())) {
            throw new DataExistenteException("El número de mesa seleccionado ya esta en uso.");
        }
    }

}
