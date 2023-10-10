
package BibliotecaV2_JDK8.BibliotecaV2_JDK8.repositorios;

import BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
}
