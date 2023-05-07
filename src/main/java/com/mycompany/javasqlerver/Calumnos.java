/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javasqlerver;

//import java.awt.Dialog;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Oscar CR
 */
public class Calumnos {
    
    public void  mostrarAlumnos(JTable paramTablaAlumnos){
        
        cconexion objetoConexion = new  cconexion();
        
        DefaultTableModel modelo = new  DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("codigo");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Edad");
        
        paramTablaAlumnos.setModel(modelo);
        
        sql= "select *from alumnos;";
        
        String [] datos = new String[5];
        
        Statement st;
        
        try {
            st = objetoConexion.establecerConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {

                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                
                modelo.addRow(datos);
                
            }
             paramTablaAlumnos.setModel(modelo);
                
                
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se mostraron los registros error:"+e.toString());
        }
        
        
    }
    
    public void seleccionarAlumno(JTable paramTablaAlumnos,JTextField paramCodigo,JTextField paramDNI,JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad ){
        
        try {
            int fila= paramTablaAlumnos.getSelectedRow();
            if (fila>=0) {
                paramCodigo.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramDNI.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 3).toString());
                paramEdad.setText(paramTablaAlumnos.getValueAt(fila, 4).toString());
               
            }else{
              JOptionPane.showMessageDialog(null, "No se encontro los registros, error:");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de seleccion, error:"+e.toString());
        }
    }
    
    public void InsertarAlumnos(JTextField paramDNI, JTextField paramNombres,JTextField paramApellidos,JTextField paramEdad){
        cconexion objetoConexion =new cconexion();
        
        String consulta = "insert into alumnos( dni, nombres, apellidos,edad) values (?,?,?,?);";
        
        try {
            CallableStatement cs = objetoConexion.establecerConnection().prepareCall(consulta);
            
            cs.setString(1, paramDNI.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setInt(4, Integer.parseInt( paramEdad.getText()));
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de Insercion, error:"+e.toString());
        }
    }
    
    public void modificarAlumos(JTextField paramCodigo, JTextField paramDNI, JTextField paramNombres,JTextField paramApellidos,JTextField paramEdad){
         cconexion objetoConexion =new cconexion();
        
        String consulta = "update alumnos set alumnos.dni=?, alumnos.nombres=?,alumnos.apellidos=?,alumnos.edad=? where alumnos.codigo = ?;";
        
        try {
            CallableStatement cs = objetoConexion.establecerConnection().prepareCall(consulta);
            
            cs.setString(1, paramDNI.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setInt(4, Integer.parseInt( paramEdad.getText()));
            cs.setInt(5, Integer.parseInt( paramCodigo.getText()));
            
            cs.execute();
                    
            JOptionPane.showMessageDialog(null, "Se modifico correctamente el alumno");
                    
                    
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de modificacion, error:"+e.toString());
        }
        
    }
    
    public void eliminarAlumnos(JTextField paramCodigo){
        cconexion objetoConexion =new cconexion();
        
        String consulta = "delete from alumnos where alumnos.codigo = ?;";
        
        try {
            CallableStatement cs = objetoConexion.establecerConnection().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt( paramCodigo.getText()));
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el alumno");
        
        
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de eliminacion, error:"+e.toString());
        }
    }

}
