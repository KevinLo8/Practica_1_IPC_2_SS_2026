/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.CompraInsumo.*;
import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class CompraInsumoDB extends ConexionDB {

    public CompraInsumoDB() throws DataBaseException {
        super();
    }

    public void agregarCompraInsumo(CompraInsumo compraInsumo) throws DataBaseException {
        String query = "INSERT INTO compra_insumo (código, fecha) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, compraInsumo.getCodigo());
            preparedStatement.setDate(2, Date.valueOf(compraInsumo.getFecha()));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la compra de insumo.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar la compra de insumo.");
        }
    }

    public void agregarDetalleCompra(DetalleCompra detalleCompra) throws DataBaseException {
        String query = "INSERT INTO detalle_compra (código, código_compra, código_insumo, cantidad, costo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, detalleCompra.getCodigo());
            preparedStatement.setString(2, detalleCompra.getCodigoCompra());
            preparedStatement.setString(3, detalleCompra.getCodigoInsumo());
            preparedStatement.setInt(4, detalleCompra.getCantidad());
            preparedStatement.setDouble(5, detalleCompra.getCosto());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el detalle de compra.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar el detalle de compra.");
        }
    }

    public boolean revisarCodigoCompra(String codigo) throws DataBaseException {
        String query = "SELECT * FROM compra_insumo WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el código.");
        }
    }

    public boolean revisarCodigoDetalle(String codigo) throws DataBaseException {
        String query = "SELECT * FROM detalle_compra WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el código.");
        }
    }

}
