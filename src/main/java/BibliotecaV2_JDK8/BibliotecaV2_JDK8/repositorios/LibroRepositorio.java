/*

los Repositorios son componentes que nos permiten: 
Crear,Modificar,Editar,Eliminar Y Buscar Objetos de nuestro Dominio

 */
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




/*Los repostorios nos van ayudar a transformar nuestro modelo relacional, 
en un modelo de objetos de nuestro dominio*/

/*estea interface Hereda de JpaRrepository,debemos indicar cual es tipo de objetos que
va manejar y cual es el tipo de dato de su llave primaria,*/

@Repository
public interface LibroRepositorio  extends JpaRepository<Libro, Long>{
    /*Generando la anotacion de query inlcuimos la consulta SQL con la busqueda especifica
    Y dentro de nuestro metodo indicamos con @Param hacemos referencia al parametro por cual buscar
    */
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo")String titulo);
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre")String nombre);
}
