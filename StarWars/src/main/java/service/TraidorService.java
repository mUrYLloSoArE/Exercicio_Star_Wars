package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TraidorService {

    private Statement statement;
    private ResultSet rs=null;
    private Scanner sc=new Scanner(System.in);
    private  Integer contador=2;
    private Integer id_traidor;


    public void denuncia(){
        System.out.println("Digite o id do possivel traidor: ");
        id_traidor=sc.nextInt();
    }

    public  void validarTraidor(Connection conn){
        denuncia();
        try {
            String comando=String.format("select * from rebeldes");
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            while (rs.next()){
                if(id_traidor.equals(rs.getInt("id_rebelde"))){
                    comando=String.format("update rebeldes set qtd_denuncias=qtd_denuncias+1 where id_rebelde='%d'",id_traidor);
                    statement=conn.createStatement();
                    statement.executeUpdate(comando);
                    System.out.println("Denuncia encaminhada! ");
                    if (contador.equals(rs.getInt("qtd_denuncias"))){
                        comando=String.format("update rebeldes set ativo=false where id_rebelde='%d'",id_traidor);
                        statement=conn.createStatement();
                        statement.executeUpdate(comando);
                        System.out.println("Encontramos a ameaça, desativar todos os acessos! ");
                    }
                }
            }

        }
        catch (SQLException e){
            System.out.println("Erro!");
        }
    }

}
