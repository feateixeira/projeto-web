package Documents.NetBeansProjects.ProjetoCrudWeb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import Documents.NetBeansProjects.ProjetoCrudWeb.LivroDAO;
import Documents.NetBeansProjects.ProjetoCrudWeb.CrudDAO;

@SessionScoped
@ManagedBean
public class LivroBean extends CrudBean<Livro, LivroDAO>{
    
    private LivroDAO livroDAO;
   
    @Override
    public LivroDAO getDao() {
        if(livroDAO == null){
            livroDAO = new LivroDAO();                    
        }
        return livroDAO;
    }
    
    @Override
    public Livro criarNovaEntidade(){
        return new Livro();
    }
}
