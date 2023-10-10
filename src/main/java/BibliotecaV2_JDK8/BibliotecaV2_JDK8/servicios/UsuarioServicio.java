/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Usuario;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.enumeraciones.Rol;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void registrar(String nombre,String email,String password,String password2) throws MiException{
           
        validar(nombre,email,password,password2);
       
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        //mediante BCryptPasswordEncoder().encode(password) codificamos la password que se alojara en la base de datos 
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        
        usuarioRepositorio.save(usuario);
           
    }
    
    private void validar(String nombre,String email,String password,String password2) throws MiException{
        
        if(nombre.isEmpty() || nombre== null){
            throw new MiException("EL nombre no puede ser nulo o estar vacio");
        }
        if(email.isEmpty() || email== null){
            throw new MiException("EL email no puede ser nulo o estar vacio");
        }
        if(password.isEmpty() || password== null){
            throw new MiException("La password no puede ser nulo o estar vacio");
        }
        if(!password.equals(password2)){
            throw new MiException("Las Contraseñas deben ser iguales");
        }
    }
    /*
    Al implentar UserDetailsService en nuestra clase deberemos indicar que se va sobre escribir este metodo loadUserByUsername
    y luego indicaremos cuales son los parametros que consideraremos para poder autenticar alos usuarios
    En nuestro caso sera atravez del email y la password
    
    */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /*
            Luego buscaremos un usuario mediante usuarioRepositorio y lo tansformaremos en un usuario
            de SpringSecurity; 
        */
        
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        
        if (usuario != null){
            /* este usuario lo importaremos de SecurityProperties.User y le indicaremos dentro de sus parametros el nombre de usario, la contraseña,
               y  una lista de permisos . 
            Esta lista de Permisos crearemos una List de objetos <GranteAuthority> que es la que va contener todos los permisos
            */
            
            List<GrantedAuthority> permisos = new ArrayList();
            
             /*
                Ahora crearemos algunos permisos para este usuario . Instanciando un objeto GrantedAuthority he indicando que es un nuevo
                SimpleGranteAuthority(), estos permisos seran dados alos usarios que tengas un rol determinado
                para ello le pasaremos como parametro , ("ROLE_"+usuario.getRol())
            */
             
             GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString()); //ROLE_USER
             
             //agragamos este objeto p ala lista anteriormente creada
             
             permisos.add(p);
            
             return new User(usuario.getEmail(),usuario.getPassword(),permisos);
             
        }else {
            
        return null;
        
        }
        //Con este hemos conseguido que cuando un usario se logee springSecurity se dirija a este metodo y le de los permisos necesarios
    }
}
