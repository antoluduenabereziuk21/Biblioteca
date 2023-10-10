/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Autor;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Editorial;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Libro;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.AutorRepositorio;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.EditorialRepositorio;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *Los servicios llevaran adelante toda al logica del negocio para cumplir los requerimientos del cliente
 * 
 */
@Service
public class LibroServicio {
    /*
    generamos un metodo para craer un libro , debemos intanciar de libroRepositorio
    @Autowired , le indicamos al servidor que esta instancia va ser inicializada por el
    esto se conoce como inyecion de dependencias, nos permitira utilizar todos los metodos que tiene LibroRepositorio
    */
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    //aquellos metodos que realizan alguna modificacion en la base de datos deben contener el @Transacional para informar que es una transacion
    @Transactional
    public void crearLibro(Long isbn,String titulo,Integer ejemplares,String idAutor, String idEditorial) throws MiException{
        
        validar(isbn,titulo, ejemplares, idAutor, idEditorial);
        
        //indicando .get() nos devolvera el autor que machee con el id pasado, y lo guardamos en autor
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial =editorialRepositorio.findById(idEditorial).get();
        Libro libro =new Libro();
        
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
                 
    }
    
    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList();
        
        libros= libroRepositorio.findAll();
        
        return libros;
    }
    
    @Transactional
    public void modfificarLibro(Long isbn,String titulo, String idAutor,String idEditorial,Integer ejemplares) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        //Optional(Obejeto Contenedor) que puede contener un valor a no , pero no nulo, si el valor esta presente nos devuelve true , 
        //y optenemos el valor si la respuesta es true con .get()
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor= new Autor();
        Editorial editorial = new Editorial();
                
        
        if(respuestaAutor.isPresent()){
           autor = respuestaAutor.get();
                    
        }
        if(respuestaEditorial.isPresent()){
             editorial = respuestaEditorial.get();
                    
        }
        
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
                    
        }
    }
    
    private void validar(Long isbn,String titulo,Integer ejemplares,String idAutor, String idEditorial)throws MiException{
        if(isbn == null){
        
            throw new MiException("El ISBN no puede ser nulo");
        }
        if( titulo.isEmpty() || titulo == null){
            throw new MiException("El Titulo no puede ser nulo o estar vacio");
        }
        if(idAutor.isEmpty() || idAutor== null){
            throw new MiException("El IdAutor no puede ser nulo o estar vacio");
        }
        if(idEditorial.isEmpty() || idEditorial== null){
            throw new MiException("El IdEditorial no puede ser nulo o estar vacio");
        }
    }
}
