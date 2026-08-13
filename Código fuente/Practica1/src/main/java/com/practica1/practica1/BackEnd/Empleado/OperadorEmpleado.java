/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Empleado;

import com.practica1.practica1.BackEnd.DataBase.EmpleadoDB;
import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import com.practica1.practica1.BackEnd.Exceptions.DataExistenteException;

/**
 *
 * @author Kevin
 */
public class OperadorEmpleado {

    public Object[][] convertirListaEmpleados(Empleado[] lista) {
        Object[][] datos = new Object[0][7];
        for (Empleado empleado : lista) {
            int index = datos.length;
            Object[][] datosTemp = new Object[index + 1][7];

            for (int i = 0; i < index; i++) {
                datosTemp[i] = datos[i];
            }

            datosTemp[index][0] = empleado.getDpi();
            datosTemp[index][1] = empleado.getNombre();
            datosTemp[index][2] = empleado.getRol();
            datosTemp[index][3] = empleado.getJornada();
            datosTemp[index][4] = empleado.getSalario();
            datosTemp[index][5] = empleado.getFechaContratacion();
            if (empleado.isHabilitado()) {
                datosTemp[index][6] = "Habilitado";
            } else {
                datosTemp[index][6] = "Deshabilitado";
            }

            datos = datosTemp;
        }
        return datos;
    }

        public Object[] convertirListaEmpleadosDPI(Empleado[] lista) {
        Object[] datos = {null};
        for (Empleado empleado : lista) {
            int index = datos.length;
            Object[] datosTemp = new Object[index + 1];

            for (int i = 0; i < index; i++) {
                datosTemp[i] = datos[i];
            }

            datosTemp[index] = empleado.getDpi();

            datos = datosTemp;
        }
        return datos;
    }

    public void revisarDPI(EmpleadoDB database, Empleado empleado) throws DataExistenteException, DataBaseException {
        if (database.revisarDPI(empleado.getDpi())) {
            throw new DataExistenteException("El DPI escrito ya está en uso ya esta en uso.");
        }
    }

}
