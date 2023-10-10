/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.controladores;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Autor;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Editorial;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Libro;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.AutorServicio;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.EditorialServicio;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    
     @GetMapping("/registrar")//localhost:8080/libro/registrar
    public String registrar(ModelMap modelo){
        
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores",autores);
        modelo.addAttribute("editoriales", editoriales);
        
        return "libro_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long isbn,@RequestParam String titulo,
            @RequestParam(required=false)  Integer ejemplares,@RequestParam String idAutor,@RequestParam String idEditorial,ModelMap modelo){
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            
            modelo.put("exito", "El libro Fue cargado con Exito");
        } catch (MiException ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            
            modelo.put("error", ex.getMessage()+"entro al catch");
            
            return "libro_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Libro> libros = libroServicio.listarLibros();
        
        modelo.addAttribute("libros", libros);
        
        return "libro_list";
    }
    
}
