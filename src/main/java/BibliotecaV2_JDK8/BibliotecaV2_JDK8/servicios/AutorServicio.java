/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Autor;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AutorServicio {
    //persistimmos en la base de datos(porque no le da el atributo de private en el video);
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    
    @Transactional
    public void crearAutor(String nombre)throws MiException{
        
        validarNombre(nombre);
    
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
                
    }
    
    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        
        autores= autorRepositorio.findAll();
        
        return autores;
    }
    @Transactional
    public void modificarAutor(String nombre, String id,Boolean estado) throws MiException{
        
        validar(nombre, id);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
        
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            
            autor.setEstado(estado);
            
            autorRepositorio.save(autor);
        }
    }
    //para traer un autor         
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
    
    private void validar(String nombre,String id) throws MiException{
        if(nombre.isEmpty() || nombre == null){
        
            throw new MiException("El Nombre no puede ser nulo");
        }
        if( id.isEmpty() || id == null){
            throw new MiException("El id no puede ser nulo o estar vacio");
        }
    }
    
    
    private void validarNombre(String nombre) throws MiException{
        if(nombre.isEmpty() || nombre == null){
        
            throw new MiException("El Nombre no puede ser nulo");
        }
        
    
    }
    
}
