package BackEnd.Insumo;

import BackEnd.DataBase.InsumoDB;
import BackEnd.Exceptions.DataBaseException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kevinl
 */
public class OperadorInsumo {

    public Object[][] convertirListaInsumos(Insumo[] lista) {
        Object[][] datos = new Object[0][6];
        for (Insumo insumo : lista) {
            int index = datos.length;
            Object[][] datosTemp = new Object[index + 1][6];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index][0] = insumo.getCodigo();
            datosTemp[index][1] = insumo.getNombre();
            datosTemp[index][2] = insumo.getUnidadMedida();
            datosTemp[index][3] = insumo.getStock();
            datosTemp[index][4] = insumo.getStockMinimo();
            datosTemp[index][5] = insumo.getCosto();

            datos = datosTemp;
        }
        return datos;
    }

    public Object[] convertirListaInsumosCodigo(Insumo[] lista) {
        Object[] datos = {null};
        for (Insumo insumo : lista) {
            int index = datos.length;
            Object[] datosTemp = new Object[index + 1];

            System.arraycopy(datos, 0, datosTemp, 0, index);

            datosTemp[index] = insumo.getCodigo();

            datos = datosTemp;
        }
        return datos;
    }

    public String crearCodigo(InsumoDB database) throws DataBaseException {
        int numero = 1;
        String codigo = String.format("I%04d", numero);
        while (database.revisarCodigo(codigo)) {
            numero++;
            codigo = String.format("I%04d", numero);
        }
        return codigo;
    }

}
