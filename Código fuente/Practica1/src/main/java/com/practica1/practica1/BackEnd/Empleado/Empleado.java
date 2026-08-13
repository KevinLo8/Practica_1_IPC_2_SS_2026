/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica1.practica1.BackEnd.Empleado;

import com.practica1.practica1.BackEnd.Exceptions.FormatoDatosException;
import java.time.LocalDate;

/**
 *
 * @author Kevin
 */
public class Empleado {

    public Empleado(String dpi, String nombre, String rol, String jornada, double salario, LocalDate fechaContratacion, boolean estadoHabilitacion) throws FormatoDatosException {
        revisarDatos(dpi, nombre, salario);
        this.dpi = dpi;
        this.nombre = nombre;
        this.rol = Rol.retornarRol(rol);
        this.jornada = Jornada.retornarJornada(jornada);
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.estadoHabilitacion = estadoHabilitacion;
    }

    private String dpi;
    private String nombre;
    private Rol rol;
    private Jornada jornada;
    private double salario;
    private LocalDate fechaContratacion;
    private boolean estadoHabilitacion;

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public double getSalario() {
        return salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public boolean isHabilitado() {
        return estadoHabilitacion;
    }

    private void revisarDatos(String dpi, String nombre, double salario) throws FormatoDatosException {
        if (dpi == null | nombre == null) {
            throw new FormatoDatosException("Llene todos los espacios necesarios.");
        }
        if (!dpi.matches("^\\d{13}$")) {
            throw new FormatoDatosException("Ingrese un número de dpi correcto.");
        }
        if (nombre.length() > 100) {
            throw new FormatoDatosException("Ingrese un nombre de tamaño menor a 100.");
        }
        if (salario <= 0) {
            throw new FormatoDatosException("Ingrese un salario mayor a 0");
        }
    }
}
