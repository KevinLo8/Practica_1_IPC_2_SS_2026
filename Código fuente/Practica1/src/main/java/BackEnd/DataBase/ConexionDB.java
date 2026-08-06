/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.DataBase;

import BackEnd.Exceptions.DataBaseException;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class ConexionDB {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "cafetería";
    public static final String USER_NAME = "admindba";
    public static final String PASSWORD = "12345";

    public static final String URL = "jdbc:mysql://"
            + IP + ":" + PUERTO + "/" + SCHEMA;

    protected Connection connection;

    public ConexionDB() throws DataBaseException {
        System.out.println("URL de conexion: " + URL);
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            lanzarError(e, "Error al conectarse con la base de datos.");
        }
    }

    private void lanzarError(SQLException e, String mensaje) throws DataBaseException {
        throw new DataBaseException(mensaje);
    }
}
