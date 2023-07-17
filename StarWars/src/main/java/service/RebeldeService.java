package service;

import connection.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RebeldeService {
    private Scanner sc=new Scanner(System.in);
    private Statement statement;
    private ResultSet rs=null;
    private String nome,genero;
    private Integer idade,id,id_inventario,qtd_denuncias;
    private boolean ativo;

    private  boolean credencial=false;

    private void menuInsert(){
        System.out.println("Digite o seu nome");
        nome = sc.next();
        System.out.println("Digite a sua idade");
        idade = sc.nextInt();
        System.out.println("Digite o seu genero");
        genero=sc.next();
        ativo=true;
        System.out.println("Digite o código da sua localização");
        id= sc.nextInt();
        System.out.println("Digite o código do seu inventário");
        id_inventario= sc.nextInt();
        qtd_denuncias=0;
    }

    public void  recrutarRebelde(Connection conn){

        try {
            menuInsert();
            String comando=String.format("insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) " +
                    "values('%s','%d','%s',%b,'%d','%d','%d');",
                     nome,idade,genero,ativo,id,id_inventario,qtd_denuncias
            );
            statement=conn.createStatement();
            statement.executeUpdate(comando);
            System.out.println("Recruta aceito");
        }catch (SQLException e){
            System.out.println("Erro ao recrutar!");
        }
    }

    public void rebeldesAlistados(Connection conn){
        try {
            String comando="select id_rebelde,nome,idade,genero,ativo,qtd_denuncias,planeta,base,arma,armadura,magia from rebeldes\n" +
                    "left join localizacao\n" +
                    "on\n" +
                    "id_rebelde_localizacao=id_localizacao\n" +
                    "left join inventario\n" +
                    "on\n" +
                    "id_rebelde_inventario=id_inventario\n" +
                    "order by id_rebelde asc\n" +
                    ";";
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            while(rs.next()){
                System.out.print("Código de indentificação -> "+rs.getInt("id_rebelde")+"\n");
                System.out.print("Nome -> "+rs.getString("nome")+"\n");
                System.out.print("Idade -> "+ rs.getInt("idade")+" anos " + "\n");
                System.out.print("Genero -> "+ rs.getString("genero")+"\n");
                System.out.print("Ativo na corporação -> "+ rs.getBoolean("ativo")+"\n");
                System.out.println("Advertencias -> " + rs.getInt("qtd_denuncias"));
                System.out.print("Planeta -> "+ rs.getString("planeta")+"\n");
                System.out.print("Base -> "+ rs.getString("base")+"\n");
                System.out.print("Inventário: "+"\n");
                System.out.print("Arma -> "+ rs.getString("arma")+"\n");
                System.out.print("Armadura -> "+ rs.getString("armadura")+"\n");
                System.out.print("Magia -> "+ rs.getString("magia")+"\n");
                System.out.println();
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta!");
        }

    }

    public void teste(Connection conn){
        try {
            String comando=String.format("select * from rebeldes");
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            while (rs.next()){
                if(!rs.getBoolean("ativo")){
                    System.out.println("ok " );
                }
            }


        }catch (SQLException e){

        }
    }

}
