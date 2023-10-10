
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
}
