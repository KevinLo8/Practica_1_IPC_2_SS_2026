/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.CompraInsumo;

import BackEnd.Exceptions.DataExistenteException;
import java.time.LocalDate;

/**
 *
 * @author Kevin
 */
public class CompraInsumo {

    private final String codigo;
    private LocalDate fecha;
    private DetalleCompra[] detalleCompras;

    public CompraInsumo(String codigo) {
        this.codigo = codigo;
        detalleCompras = new DetalleCompra[0];
        fecha = LocalDate.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void agregarDetalle(DetalleCompra detalleCompra) throws DataExistenteException {

        chequerDetalle(detalleCompra);

        int index = detalleCompras.length;
        DetalleCompra[] detallesTemp = new DetalleCompra[index + 1];

        System.arraycopy(detalleCompras, 0, detallesTemp, 0, index);

        detallesTemp[index] = detalleCompra;
        detalleCompras = detallesTemp;

    }

    private void chequerDetalle(DetalleCompra detalleCompra) throws DataExistenteException {
        int index = detalleCompras.length;
        for (int i = 0; i < index; i++) {
            if (detalleCompras[i].getCodigoInsumo().equals(detalleCompra.getCodigoInsumo())) {
                throw new DataExistenteException("Seleccione un insumo que no este agregado.");
            }
        }
    }

    public DetalleCompra[] getDetalleCompras() {
        return detalleCompras;
    }

}
