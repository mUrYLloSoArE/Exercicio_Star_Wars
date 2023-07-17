package service;

import controller.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ComprasService {

    private Statement statement;
    private ResultSet rs=null;
    private Scanner sc = new Scanner(System.in);
    private Integer id_rebelde,id_compra;

    private Menu menu;
    public void rebeldeItens(Connection conn){
        try {
            System.out.println("Digite o Id do Rebelde para verificar");
            id_rebelde=sc.nextInt();
            String nomeRebelde=String.format(
                    "select distinct  rebeldes.nome,rebeldes.ativo from compradores \n" +
                    "inner join rebeldes\n" +
                    "on id_compras_rebelde = id_rebelde \n" +
                    "where id_compras_rebelde='%d'\n" +
                    ";",id_rebelde
            );
            statement=conn.createStatement();
            rs=statement.executeQuery(nomeRebelde);
            while(rs.next()){
                System.out.print("Nome -> "+ rs.getString("nome")+"\n");
                System.out.print("Status -> "+ rs.getBoolean("ativo")+"\n");
                System.out.println();
            }

            String itensRebelde=String.format("select  compras.item from compradores \n" +
                    "inner join compras \n" +
                    "on id_compras_item = id_compras\n" +
                    "where id_compras_rebelde='%d'\n" +
                    ";",id_rebelde);

            rs=statement.executeQuery(itensRebelde);
            System.out.println("Seus Itens:");
            while(rs.next()){
                System.out.println(rs.getString("item"));
            }

        }
        catch (SQLException e){
            System.out.println("Erro na consulta!");
        }
    }
    public void compraItens(Connection conn){
        try {
            System.out.println("Bem vindo a nossa base de compras!");
            String comando="select * from compras";
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            System.out.println("Itens disponíveis: ");
            while(rs.next()){
                System.out.print("Id -> "+ rs.getInt("id_compras")+"\n");
                System.out.print("Item -> "+ rs.getString("item"));
                System.out.println();
            }


            System.out.println("Digite o Id do Rebelde para verificaçao!");
            id_rebelde=sc.nextInt();

            String comandos=String.format("select ativo from rebeldes where id_rebelde='%d'",id_rebelde);
            statement=conn.createStatement();
            rs=statement.executeQuery(comandos);

            while(rs.next()){
                if(rs.getBoolean("ativo")) {
                    System.out.println("Digite o Id do item que queira comprar!");
                    id_compra=sc.nextInt();
                    String insere=String.format("insert  into compradores(id_compras_item,id_compras_rebelde) " +
                            "values ('%d','%d');\n",id_compra,id_rebelde);
                    System.out.println("Compra Realizada!");
                    statement=conn.createStatement();
                    statement.executeUpdate(insere);
                }else {
                    System.out.println("Traidores não são bem vindos aqui!");
                    System.out.println();
                }

            }

        }
        catch (SQLException e){
            System.out.println("Erro ao fazer a compra!");
        }
    }

}
