/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javasqlerver;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar CR
 */
public class cconexion {
    Connection conectar = null;
    String usuario= "root3";
    String contraseña ="root3";
    String bd = "alumnosDB";
    String ip = "localhost";
    String puerto = "50648";
    //1433
    //50648
    
    public Connection establecerConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String cadena ="jdbc:sqlserver://"+ip+":"+puerto+";"+"databaseName="+bd+";"+
                    "encrypt=true;trustServerCertificate=true";
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            
           // JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conecto correctamente a la base de datos ");
        }
        return conectar;
    }
}
