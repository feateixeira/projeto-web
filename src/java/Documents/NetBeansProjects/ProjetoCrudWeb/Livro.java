
package Documents.NetBeansProjects.ProjetoCrudWeb;

import java.sql.Date;
import java.util.Objects;


public class Livro {
    
    private Integer id;

    
    private String titulo;
    private String autor;
    private String editora;
    private String anoPubli;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
       
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(String anoPubli) {
        this.anoPubli = anoPubli;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
