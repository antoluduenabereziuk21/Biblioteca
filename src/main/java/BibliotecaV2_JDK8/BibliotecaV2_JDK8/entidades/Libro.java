
/*
Las Entidades nbos sriven par modelar los objetos que queremos persistir , 
y los repositorios son interfaces entre el modelo de objetos y la base de subyacente
*/

package BibliotecaV2_JDK8.BibliotecaV2_JDK8.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//informamos que es una entidad
@Entity
public class Libro {
    
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;
    private Boolean estado;
    
    //para registrar la fecha de alta
    @Temporal(TemporalType.DATE)
    private Date alta;
    
    //Indicamos que muchos libros van a tener un autor
    @ManyToOne
    private Autor autor;
    //Indicamos que muchos libros van a tener una editorial
    @ManyToOne
    private Editorial editorial;

    public Libro() {
        this.estado= true;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
}
