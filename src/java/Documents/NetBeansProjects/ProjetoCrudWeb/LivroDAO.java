package Documents.NetBeansProjects.ProjetoCrudWeb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO  implements CrudDAO<Livro>{
    
    @Override
    public void salvar(Livro livro) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(livro.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `livro`(`titulo`,`autor`,`editora`,`anoPubli`) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update livro set titulo=?, autor=?, editora=?, anoPubli=? where id=?");
                ps.setInt(5, livro.getId());
            }            
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getEditora());
            ps.setString(4, livro.getAnoPubli());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex){
            throw new ErroSistema("Erro ao tentar salvar", ex);
        }
    }
    
    @Override
    public void deletar(Livro livro) throws ErroSistema{
        try{
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from livro where id = ?");
            ps.setInt(1, livro.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o livro!", ex);
        }
    }

    @Override
    public List<Livro> buscar() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from livro");
            ResultSet resultSet = ps.executeQuery();
            List<Livro> livros = new ArrayList<>();
            while(resultSet.next()){
                Livro livro = new Livro();
                livro.setId(resultSet.getInt("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setEditora(resultSet.getString("editora"));
                livro.setAnoPubli(resultSet.getString("anoPubli"));
            }
            FabricaConexao.fecharConexao();
            return livros;
        }catch(SQLException ex){
            throw  new ErroSistema("Erro ao buscar livros", ex);
        }
    }
}

   

