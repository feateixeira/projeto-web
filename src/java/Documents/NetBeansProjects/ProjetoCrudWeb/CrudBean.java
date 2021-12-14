package Documents.NetBeansProjects.ProjetoCrudWeb;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Documents.NetBeansProjects.ProjetoCrudWeb.ErroSistema;
import Documents.NetBeansProjects.ProjetoCrudWeb.CrudDAO;

@ManagedBean
@SessionScoped
public  abstract class CrudBean<E, D extends CrudDAO> {
    
    private String estadoTela = "buscar";
    
    private E entidade;
    private List<E> entidades;
    
    public void novo(){
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }
    public void salvar(){
      try{
        getDao().salvar(entidade);
        entidade = criarNovaEntidade();
        adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        mudarParaBusca();
      }catch (ErroSistema ex) {
          Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
          adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
      }
    }
    public void editar(){
        this.entidade = entidade;
        mudarParaEdita();
    }
    public void deletar(){
        try{
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch(ErroSistema ex){
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void buscar(){
        if(isBusca() == false){
            mudarParaBusca();
            return;
        }
        try{
            entidades = getDao().buscar();
            if(entidades == null || entidades.size() < 1){
                adicionarMensagem("Nao tem nenhum livro cadastrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex){
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    public abstract D getDao();
    public abstract E criarNovaEntidade();
    
    public boolean isInseri(){
        return "inserir".equals(estadoTela);
    }
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }
    
    public void mudarParaInseri(){
        estadoTela = "inseri";
    }
    public void mudarParaEdita(){
        estadoTela = "edita";
    }
    public void mudarParaBusca(){
        estadoTela = "busca";
    }

    

    
}
