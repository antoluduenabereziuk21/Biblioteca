/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario {
    @Id
    //la estartegia para generar el id sera uuid que nos autogenerara Un Id Alfanumérico
    @GeneratedValue(generator = "uuid")
    //En caso de fallar el primer generador , generamos una estaretegia nueva para salvar errores
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
