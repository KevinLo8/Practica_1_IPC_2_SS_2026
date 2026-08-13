/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Receta;

import com.practica1.practica1.BackEnd.DataBase.InsumoDB;
import com.practica1.practica1.BackEnd.DataBase.RecetaDB;
import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;
import com.practica1.practica1.BackEnd.Insumo.Insumo;

/**
 *
 * @author kevinl
 */
public class OperadorReceta {

    public Object[][] convertirLista(Receta[] lista) {
        Object[][] datos = new Object[0][4];
        for (Receta receta : lista) {
            int index = datos.length;
            Object[][] datosTemp = new Object[index + 1][4];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index][0] = receta.getCodigoInsumo();
            datosTemp[index][1] = receta.getNombre();
            datosTemp[index][2] = receta.getCantidad();
            datosTemp[index][3] = receta.getUnidadDeMedida();

            datos = datosTemp;
        }
        return datos;
    }

    public Object[] convertirListaCodigo(Receta[] lista) {
        Object[] datos = new Object[1];
        for (Receta receta : lista) {
            int index = datos.length;
            Object[] datosTemp = new Object[index + 1];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index] = receta.getCodigo();

            datos = datosTemp;
        }
        return datos;
    }

    public String crearCodigo(Receta[] recetario, RecetaDB database) throws DataBaseException {
        int numero = 1;
        String codigo = String.format("R%04d", numero);
        while (revisarExistenciaCodigo(codigo, recetario, database)) {
            numero++;
            codigo = String.format("R%04d", numero);
        }
        return codigo;
    }

    private boolean revisarExistenciaCodigo(String codigo, Receta[] recetario, RecetaDB database) throws DataBaseException {
        if (database.revisarCodigo(codigo)) {
            return true;
        }
        for (Receta receta : recetario) {
            if (codigo.equals(receta.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public Receta[] crearReceta(String codigo, RecetaDB database) throws FormatoDatosException, DataBaseException {
        Receta[] recetas = database.pedirRecetas();
        InsumoDB databaseInsumo = new InsumoDB();
        
        for (Receta receta : recetas) {
            Insumo insumo = databaseInsumo.pedirInsumo(receta.getCodigoInsumo());
            receta.setNombre(insumo.getNombre());
            receta.setUnidadDeMedida(insumo.getUnidadMedida());
        }
        
        return recetas;
    }
}
