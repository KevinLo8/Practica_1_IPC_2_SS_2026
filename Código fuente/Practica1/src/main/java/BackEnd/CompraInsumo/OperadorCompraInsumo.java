/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.CompraInsumo;

import BackEnd.DataBase.CompraInsumoDB;
import BackEnd.Exceptions.DataBaseException;

/**
 *
 * @author Kevin
 */
public class OperadorCompraInsumo {

    public String crearCodigoCompra(CompraInsumoDB database) throws DataBaseException {
        int numero = 1;
        String codigo = String.format("Co%04d", numero);
        while (database.revisarCodigoCompra(codigo)) {
            numero++;
            codigo = String.format("Co%04d", numero);
        }
        return codigo;
    }

    public String crearCodigoDetalle(DetalleCompra[] detalleCompras, CompraInsumoDB database) throws DataBaseException {
        int numero = 1;
        String codigo = String.format("DCo%06d", numero);
        while (revisarExistenciaCodigo(codigo, detalleCompras, database)) {
            numero++;
            codigo = String.format("DCo%06d", numero);
        }
        return codigo;
    }

    private boolean revisarExistenciaCodigo(String codigo, DetalleCompra[] detalleCompras, CompraInsumoDB database) throws DataBaseException {
        if (database.revisarCodigoDetalle(codigo)) {
            return true;
        }
        for (DetalleCompra detalleCompra : detalleCompras) {
            if (codigo.equals(detalleCompra.getCodigo())) {
                return true;
            }
        }
        return false;
    }
}
