package Documents.NetBeansProjects.ProjetoCrudWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/sistema-livros";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConexao() throws ErroSistema{
        if(conexao == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
                } catch (SQLException ex) {
                    throw new ErroSistema("Nao foi possivel conectar ao Banco de dados", ex);
                } catch (ClassNotFoundException ex) {
                    throw new ErroSistema("O driver do db nao foi encontrado", ex);
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex){
                throw new ErroSistema("Erro ao fechar conexão com o db", ex);
            }
        }
    }
}
