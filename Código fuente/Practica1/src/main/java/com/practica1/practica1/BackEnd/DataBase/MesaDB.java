/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.Exceptions.*;
import com.practica1.practica1.BackEnd.Mesa.Mesa;
import java.sql.*;

/**
 *
 * @author kevinl
 */
public class MesaDB extends ConexionDB{

    public MesaDB() throws DataBaseException {
        super();
    }

    public Mesa pedirMesa(int numero) throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM mesa WHERE número = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();

            
            if (resultSet.next()) {
                Mesa mesa = new Mesa(resultSet.getInt("número"), resultSet.getInt("capacidad"), resultSet.getString("estado"));
                return mesa;
            } else {
                throw new DataBaseException("No se ha encontrado la mesa con el número seleccionado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar la mesa.");
        }
    }

    public Mesa[] pedirListado() throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM mesa";
        Mesa[] lista = new Mesa[0];

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int index = lista.length;

                Mesa[] listaTemp = new Mesa[index + 1];
                Mesa mesa = new Mesa(resultSet.getInt("número"), resultSet.getInt("capacidad"), resultSet.getString("estado"));

                System.arraycopy(lista, 0, listaTemp, 0, index);
                listaTemp[index] = mesa;

                lista = listaTemp;
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar las mesas.");
        }
        return lista;
    }
    
    public void agregarMesa(Mesa mesa) throws DataBaseException {
        String query = "INSERT INTO mesa (número, capacidad) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mesa.getNumero());
            preparedStatement.setInt(2, mesa.getCapacidad());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la mesa.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar la mesa.");
        }
    }

    public void editarMesa(Mesa mesa, int numero) throws DataBaseException {
        String query = "UPDATE mesa SET número = ?, capacidad = ? WHERE número = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mesa.getNumero());
            preparedStatement.setInt(2, mesa.getCapacidad());
            preparedStatement.setInt(3, numero);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al editar la mesa.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar la mesa.");
        }
    }

    public boolean revisarNumero(int numero) throws DataBaseException {
        String query = "SELECT * FROM mesa WHERE número = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el número.");
        }
    }

}
