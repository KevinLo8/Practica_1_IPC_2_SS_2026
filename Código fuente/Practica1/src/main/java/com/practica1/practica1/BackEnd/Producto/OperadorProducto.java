/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Producto;

import com.practica1.practica1.BackEnd.DataBase.ProductoDB;
import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import com.practica1.practica1.BackEnd.Receta.Receta;

/**
 *
 * @author kevinl
 */
public class OperadorProducto {
    
    public Object[][] convertirListaProductos(Producto[] lista) {
        Object[][] datos = new Object[0][4];
        for (Producto producto : lista) {
            int index = datos.length;
            Object[][] datosTemp = new Object[index + 1][4];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index][0] = producto.getCodigo();
            datosTemp[index][1] = producto.getNombre();
            datosTemp[index][2] = producto.getCategoria();
            datosTemp[index][3] = producto.getPrecio();

            datos = datosTemp;
        }
        return datos;
    }

    public Object[] convertirListaProductosCodigo(Producto[] lista) {
        Object[] datos = new Object[1];
        for (Producto producto : lista) {
            int index = datos.length;
            Object[] datosTemp = new Object[index + 1];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index] = producto.getCodigo();

            datos = datosTemp;
        }
        return datos;
    }

    public String crearCodigo(ProductoDB database) throws DataBaseException {
        int numero = 1;
        String codigo = String.format("P%04d", numero);
        while (database.revisarCodigo(codigo)) {
            numero++;
            codigo = String.format("P%04d", numero);
        }
        return codigo;
    }

}
