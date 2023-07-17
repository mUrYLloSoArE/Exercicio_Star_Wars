package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conn=null;

    public Connection conectar_Banco_Dados(){
        try{
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres"
                    ,"postgres","admin");
        }catch(SQLException e){
            System.out.println("Erro, não foi possivel fazer a conexão com o banco de dados! ");
        }
        return conn;
    }

}