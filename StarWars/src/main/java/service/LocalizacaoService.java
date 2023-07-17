package service;

import connection.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LocalizacaoService {
    private Statement statement;
    private Scanner sc=new Scanner(System.in);
    private ResultSet rs=null;
    private Integer id,id_rebelde;

    public void mapaGaláxia(Connection conn){
        try {
            String comando="select * from localizacao";
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            while(rs.next()){
                System.out.print("Código de indentificação -> "+rs.getInt("id_localizacao")+"\n");
                System.out.print("Planeta -> "+ rs.getString("planeta")+"\n");
                System.out.print("Base -> "+ rs.getString("base")+"\n");
                System.out.println();
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta!");
        }
    }

    private void menuAtualizar(){
        System.out.println("Digite o código da sua localização atual:");
        id=sc.nextInt();
        System.out.println("Digite o seu identificador para atualizar:");
        id_rebelde=sc.nextInt();
    }

    public void  atualizarLocalização(Connection conn){
        try {
            menuAtualizar();
            String comando=String.format("update rebeldes set id_rebelde_localizacao='%d' where id_rebelde='%d'",id,id_rebelde);
            statement=conn.createStatement();
            statement.executeUpdate(comando);
            System.out.println("Localização Atualizada! ");
        }
        catch (SQLException e){
            System.out.println("Erro ao atualizar a localizção");
        }
    }

}
