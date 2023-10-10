/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.controladores;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.excepciones.MiException;
import BibliotecaV2_JDK8.BibliotecaV2_JDK8.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Indicamos a spring que esta class va ser de tipo Controller y mediante
 * @RequestMapping cual va ser la ruta que va escuchar a este controlador En
 * este caso al inicar ya empezaria escuchar por indicar la ruta raiza ("/")
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    /*  !Este metodo se ejecutara cuando iniciemos nuestra aplicacion
        Luego mediante @GetMapping (metodo HTTP) devoleremos el documento index.html que sera nuestra vista
     */
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo) {

        try {

            usuarioServicio.registrar(nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

}
