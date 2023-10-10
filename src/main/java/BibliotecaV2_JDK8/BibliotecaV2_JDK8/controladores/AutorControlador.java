/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.controladores;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Autor;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 *
 *creamos un nuevo controlador para autor
 */
@Controller
@RequestMapping("/autor")//localhost:8080/autor
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar")//localhost:8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }
    
    /*
    esta metodo recibe el action="/autor/registro" con el valor del input name="nombre"
    y se lo envia al servicio para cargar el autor
    */
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
         //como los metodos de autorServicio manejan errores debemos manejar la peticion 
         //entre un block trycath
        try {
            autorServicio.crearAutor(nombre);
            
            modelo.put("exito", "El Autor Fue cargado con Exito");
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            
             modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Autor> autores = autorServicio.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
    //añadimos un path variable /{id}
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre,Boolean estado,ModelMap modelo){
        try {
            autorServicio.modificarAutor(nombre, id,estado);
            //nuestro redirecionamiento hira a autor/lista(../lista)
            return  "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
        }
        return "autor_modificar.html";
    }
    
}
