/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.DataBase;

import com.practica1.practica1.BackEnd.Empleado.Empleado;
import com.practica1.practica1.BackEnd.Exceptions.*;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class EmpleadoDB extends ConexionDB {

    public EmpleadoDB() throws DataBaseException {
        super();
    }

    public Empleado pedirEmpleado(String dpi) throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM empleado WHERE dpi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, dpi);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Empleado empleado = new Empleado(resultSet.getString("dpi"), resultSet.getString("nombre"), resultSet.getString("rol"),
                        resultSet.getString("jornada_laboral"), resultSet.getDouble("salario"), resultSet.getDate("fecha_contratación").toLocalDate(),
                        resultSet.getBoolean("estado_habilitación"));
                return empleado;
            } else {
                throw new DataBaseException("No se ha encontrado el empleado con el dpi seleccionado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los empleados.");
        }
    }

    public Empleado[] pedirListado() throws DataBaseException, FormatoDatosException {
        String query = "SELECT * FROM empleado";
        Empleado[] lista = new Empleado[0];

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int index = lista.length;

                Empleado[] listaTemp = new Empleado[index + 1];
                Empleado empleado = new Empleado(resultSet.getString("dpi"), resultSet.getString("nombre"), resultSet.getString("rol"),
                        resultSet.getString("jornada_laboral"), resultSet.getDouble("salario"), resultSet.getDate("fecha_contratación").toLocalDate(),
                        resultSet.getBoolean("estado_habilitación"));

                System.arraycopy(lista, 0, listaTemp, 0, index);
                listaTemp[index] = empleado;

                lista = listaTemp;
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los empleados.");
        }
        return lista;
    }

    public void agregarEmpleado(Empleado empleado) throws DataBaseException {
        String query = "INSERT INTO empleado (dpi, nombre, rol, jornada_laboral, salario, fecha_contratación) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empleado.getDpi());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, empleado.getRol().toString());
            preparedStatement.setString(4, empleado.getJornada().toString());
            preparedStatement.setDouble(5, empleado.getSalario());
            preparedStatement.setDate(6, Date.valueOf(empleado.getFechaContratacion()));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el empleado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al guardar el empleado.");
        }
    }

    public void editarEmpleado(Empleado empleado) throws DataBaseException {
        String query = "UPDATE empleado SET nombre = ?, rol = ?, jornada_laboral = ?, salario = ?, fecha_contratación = ? WHERE dpi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getRol().toString());
            preparedStatement.setString(3, empleado.getJornada().toString());
            preparedStatement.setDouble(4, empleado.getSalario());
            preparedStatement.setDate(5, Date.valueOf(empleado.getFechaContratacion()));
            preparedStatement.setString(6, empleado.getDpi());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al editar el empleado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar el empleado.");
        }
    }

    public void cambiarEstado(String dpi, boolean estado) throws DataBaseException {
        String query = "UPDATE empleado SET estado_habilitación = ? WHERE dpi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, estado);
            preparedStatement.setString(2, dpi);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al cambiar el estado de habilitación del empleado.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al cambiar el estado de habilitación del empleado.");
        }
    }
}
