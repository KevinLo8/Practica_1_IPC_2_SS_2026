/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.Exceptions.*;
import com.practica1.practica1.BackEnd.Receta.Receta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevinl
 */
public class RecetaDB extends ConexionDB {

    public RecetaDB() throws DataBaseException {
        super();
    }

    public Receta[] pedirRecetas() throws FormatoDatosException, DataBaseException {
        String query = "SELECT * FROM receta";
        Receta[] lista = new Receta[0];

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Receta receta = new Receta(resultSet.getString("código"), resultSet.getString("código_producto"),
                        resultSet.getString("código_insumo"), resultSet.getDouble("cantidad"));

                lista = addReceta(receta, lista);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los productos.");
        }
        return lista;
    }

        private Receta[] addReceta(Receta receta, Receta[] lista) throws SQLException, FormatoDatosException {
        int index = lista.length;

        Receta[] listaTemp = new Receta[index + 1];

        System.arraycopy(lista, 0, listaTemp, 0, index);
        listaTemp[index] = receta;

        return listaTemp;
    }

public void agregarReceta(Receta receta) throws DataBaseException {
        String query = "INSERT INTO receta (código, código_producto, código_insumo, cantidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, receta.getCodigo());
            preparedStatement.setString(2, receta.getCodigoProducto());
            preparedStatement.setString(3, receta.getCodigoInsumo());
            preparedStatement.setDouble(4, receta.getCantidad());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la receta.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar la receta.");
        }
    }

    public void editarReceta(Receta receta) throws DataBaseException {
        String query = "UPDATE receta SET código_producto = ?, código_insumo = ?, cantidad = ? WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, receta.getCodigoProducto());
            preparedStatement.setString(2, receta.getCodigoInsumo());
            preparedStatement.setDouble(3, receta.getCantidad());
            preparedStatement.setString(4, receta.getCodigo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al editar la receta.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar la receta.");
        }
    }
    
    public void eliminarReceta(String codigo) throws DataBaseException {
        String query = "DELETE FROM receta WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al eliminar la receta.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al eliminar la receta.");
        }
    }
    
    public boolean revisarCodigo(String codigo) throws DataBaseException {
        String query = "SELECT * FROM receta WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el código.");
        }
    }

}
