/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.Exceptions.*;
import com.practica1.practica1.BackEnd.Insumo.Insumo;
import java.sql.*;

/**
 *
 * @author kevinl
 */
public class InsumoDB extends ConexionDB {

    public InsumoDB() throws DataBaseException {
        super();
    }

    public Insumo pedirInsumo(String codigo) throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM insumo WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Insumo insumo = new Insumo(resultSet.getString("código"), resultSet.getString("nombre"), resultSet.getString("unidad_de_medida"),
                        resultSet.getDouble("cantidad_stock"), resultSet.getDouble("stock_minimo"), resultSet.getDouble("costo"));
                return insumo;
            } else {
                throw new DataBaseException("No se ha encontrado el insumo con el código seleccionado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el insumo.");
        }
    }

    public Insumo[] pedirListado() throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM insumo";
        Insumo[] lista = new Insumo[0];

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int index = lista.length;

                Insumo[] listaTemp = new Insumo[index + 1];
                Insumo insumo = new Insumo(resultSet.getString("código"), resultSet.getString("nombre"), resultSet.getString("unidad_de_medida"),
                        resultSet.getDouble("cantidad_stock"), resultSet.getDouble("stock_minimo"), resultSet.getDouble("costo"));

                System.arraycopy(lista, 0, listaTemp, 0, index);
                listaTemp[index] = insumo;

                lista = listaTemp;
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los insumos.");
        }
        return lista;
    }

    public boolean revisarCodigo(String codigo) throws DataBaseException {
        String query = "SELECT * FROM insumo WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el código.");
        }
    }

    public void agregarInsumo(Insumo insumo) throws DataBaseException {
        String query = "INSERT INTO insumo (código, nombre, unidad_de_medida, cantidad_stock, stock_minimo, costo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, insumo.getCodigo());
            preparedStatement.setString(2, insumo.getNombre());
            preparedStatement.setString(3, insumo.getUnidadMedida());
            preparedStatement.setDouble(4, insumo.getStock());
            preparedStatement.setDouble(5, insumo.getStockMinimo());
            preparedStatement.setDouble(6, insumo.getCosto());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el insumo.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar el insumo.");
        }
    }

    public void editarInsumo(Insumo insumo) throws DataBaseException {
        String query = "UPDATE insumo SET nombre = ?, unidad_de_medida = ?, cantidad_stock = ?, stock_minimo = ?, costo = ? WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, insumo.getNombre());
            preparedStatement.setString(2, insumo.getUnidadMedida());
            preparedStatement.setDouble(3, insumo.getStock());
            preparedStatement.setDouble(4, insumo.getStockMinimo());
            preparedStatement.setDouble(5, insumo.getCosto());
            preparedStatement.setString(6, insumo.getCodigo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al editar el insumo.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar el insumo.");
        }
    }

}
