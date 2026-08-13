/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.practica1.practica1;

import com.practica1.practica1.FrontEnd.FramePrincipal;
import java.util.Locale;

/**
 *
 * @author Kevin
 */
public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("es-GT"));
        FramePrincipal frame_Principal = new FramePrincipal();
        frame_Principal.setVisible(true);
    }
}
