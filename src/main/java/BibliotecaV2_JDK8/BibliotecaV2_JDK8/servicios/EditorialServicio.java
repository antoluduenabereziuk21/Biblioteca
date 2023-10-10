/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Editorial;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre)throws MiException{
    
        validarNombre(nombre);
        
        Editorial editorial= new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList();
        
        editoriales= editorialRepositorio.findAll();
        
        return editoriales;
    }
    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException{
        
        validar(nombre, id);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()){
        
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
    }
    
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
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
