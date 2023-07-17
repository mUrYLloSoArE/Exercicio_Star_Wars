package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelatorioService {

    private Statement statement;
    private ResultSet rs=null;
    private int rebeldes = 0;
    private int traidores = 0;
    private int total;
    public  void relatorio(Connection conn){
        try {
            String comando="select ativo from rebeldes ;";
            statement=conn.createStatement();
            rs=statement.executeQuery(comando);
            while(rs.next()){
                if(rs.getBoolean("ativo")==true) {
                    rebeldes = rebeldes + 1;
                }else {
                    traidores = traidores +1;
                }

            }
            total = rebeldes + traidores;
            System.out.println("Total de Rebeldes: " + ((rebeldes*100)/total) + "%");
            System.out.println("Total de Traidores: " + ((traidores*100)/total) + "%");
            }
        catch (SQLException e){
            System.out.println("Erro na consulta!");
        }
    }

}
