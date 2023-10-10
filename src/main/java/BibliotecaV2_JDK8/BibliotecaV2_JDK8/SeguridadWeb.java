package BibliotecaV2_JDK8.BibliotecaV2_JDK8;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
Aqui es Donde cargaremos toda la seguridad de nuestra web

En primer Lugar debemos indicar a spring que este archivo sera quien llevara a cabo este rol

*/
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
//al extender de web security Adapter nos permite sobre escribir los metodos que vienen por defecto
public class SeguridadWeb extends WebSecurityConfigurerAdapter{
    
    //para poder encriptar la password primero instaciamos al servicio y generamos un nuevo metodo configureGlobal
    @Autowired
    public UsuarioServicio usuarioServicio;
    /*
    el metodo configureGlobal recibe como parametro (AuthenticationManagerBuilder auth) que es propio de 
    spring security, luego configuraremos el manejador de seguridad que es userDetailsService que maneja el servicio de 
    usuarioServicio quien es el que autentica el usuario , para luego mediante passwordEncoder(new BCryptPasswordEncoder()) codificaremos
    la password en un texto plano
    */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    /*debemos indicar que que sobree escribiremos el metodo configure con el decorador @Override
    
    aqui mediante este objeto http le indicaremos las autorizaciones de distintos parametros 
    mediante autorizeRequest().antMatchers("/css/*","/js/*","/img/*","/**").permitAll();
    le permitiremos acceder a cualkier archivo estatico cuando machee con los archivos que le 
    indiquemos dentro de los parentesis
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                authorizeRequests().
                antMatchers("/css/*","/js/*","/img/*","/**").
                permitAll();
    }
    
}
