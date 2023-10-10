/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.controladores;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Editorial;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.EditorialServicio;
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



@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    
     @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/registrar")//localhost:8080/editorial/registrar
    public String registrar(){
        return "editorial_form.html";
    }
    
    /*
    esta metodo recibe el action="/autor/registro" con el valor del input name="nombre"
    y se lo envia al servicio para cargar el autor
    */
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
         try {
             //como los metodos de autorServicio manejan errores debemos manejar la peticion
             //entre un block trycath
             editorialServicio.crearEditorial(nombre);
         } catch (MiException ex) {
             Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
             return "editorial_form.html";
         }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list.html";
    }
     @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        modelo.put("editorial", editorialServicio.getOne(id));
        return "editorial_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre,ModelMap modelo){
        try {
            editorialServicio.modificarEditorial(nombre, id);
            //nuestro redirecionamiento hira a autor/lista(../lista)
            return  "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
        }
        return "editorial_modificar.html";
    }
    
}
