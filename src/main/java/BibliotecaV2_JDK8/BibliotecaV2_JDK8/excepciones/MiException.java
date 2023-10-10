/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones;

/**
 *
 * Creando esta exception diferenciamos errores propios de nuestra logica de negocio
 * de errores y bugs propios del sistemas
 */
public class MiException extends Exception{

    public MiException(String msg) {
        super(msg);
    }
    
}
