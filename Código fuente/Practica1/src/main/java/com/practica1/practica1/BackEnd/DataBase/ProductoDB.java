/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.Exceptions.DataBaseException;
import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;
import com.practica1.practica1.BackEnd.Producto.Producto;
import java.sql.*;

/**
 *
 * @author kevinl
 */
public class ProductoDB extends ConexionDB {

    public ProductoDB() throws DataBaseException {
        super();
    }

    public Producto pedirProducto(String codigo) throws FormatoDatosException, DataBaseException {
        String query = "SELECT * FROM producto WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Producto producto = new Producto(resultSet.getString("código"), resultSet.getString("nombre"), resultSet.getString("Categoria"),
                        resultSet.getDouble("precio"));
                return producto;
            } else {
                throw new DataBaseException("No se ha encontrado el producto con el código seleccionado.");
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el producto.");
        }
    }

    public Producto[] pedirListado() throws FormatoDatosException, DataBaseException {
        String query = "SELECT * FROM producto";
        Producto[] lista = new Producto[0];

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto(resultSet.getString("código"), resultSet.getString("nombre"), resultSet.getString("Categoria"),
                        resultSet.getDouble("precio"));

                lista = addProducto(producto, lista);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los productos.");
        }
        return lista;
    }

    private Producto[] addProducto(Producto producto, Producto[] lista) throws SQLException, FormatoDatosException {
        int index = lista.length;

        Producto[] listaTemp = new Producto[index + 1];

        System.arraycopy(lista, 0, listaTemp, 0, index);
        listaTemp[index] = producto;

        return listaTemp;
    }

    public void agregarProducto(Producto producto) throws DataBaseException {
        String query = "INSERT INTO producto (código, nombre, categoria, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, producto.getCodigo());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setString(3, producto.getCategoria().toString());
            preparedStatement.setDouble(4, producto.getPrecio());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el producto.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar el producto.");
        }
    }

    public void editarProducto(Producto producto) throws DataBaseException {
        String query = "UPDATE producto SET nombre = ?, categoria = ?, precio = ? WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getCategoria().toString());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setString(4, producto.getCodigo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al editar el producto.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar el producto.");
        }
    }

    public boolean revisarCodigo(String codigo) throws DataBaseException {
        String query = "SELECT * FROM producto WHERE código = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DataBaseException("Error al revisar el código.");
        }
    }

}
