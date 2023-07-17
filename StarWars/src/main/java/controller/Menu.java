package controller;

import connection.Conexao;
import service.*;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
   private RebeldeService rebelService = new RebeldeService();
   private LocalizacaoService localizacaoService= new LocalizacaoService();
   private RelatorioService relatorioService = new RelatorioService();
   private TraidorService traidorService = new TraidorService();
   private ComprasService comprasService = new ComprasService();
   private Scanner sc=new Scanner(System.in);
   private int opcao;
   private Conexao conexao = new Conexao();
   private Connection conectar=conexao.conectar_Banco_Dados();
    public void menu(){
        System.out.println("Bem vindo a nossa base de dados do exercito galáctico");
        System.out.println("0- Encerrar ");
        System.out.println("1- Listar Rebeldes ");
        System.out.println("2- Recrutar Rebeldes ");
        System.out.println("3- Ver o mapa da galáxia ");
        System.out.println("4- Atualizar a sua localização ");
        System.out.println("5- Relátorio de traidores e rebeldes ");
        System.out.println("6- Reportar um traidor ");
        System.out.println("7- Rebeldes e seus itens ");
        System.out.println("8- Base de compras ");
        System.out.println("Digite uma das opções: ");
        opcao=sc.nextInt();
   }

   public void  opcaoSelecionada(){
       switch (opcao){
           case 0:
               System.out.println("Execução encerrada!");
               System.exit(0);
               break;
           case 1:
               rebelService.recrutarRebelde(conectar);
               break;
           case 2:
               rebelService.rebeldesAlistados(conectar);
               break;
           case 3:
               localizacaoService.mapaGaláxia(conectar);
               break;
           case 4:
               localizacaoService.atualizarLocalização(conectar);
               break;
           case 5:
               relatorioService.relatorio(conectar);
               break;
           case 6:
               traidorService.validarTraidor(conectar);
               break;
           case 7:
               comprasService.rebeldeItens(conectar);
               break;
           case 8:
               comprasService.compraItens(conectar);
               break;
           default:
               System.out.println("Opção inválida digite novamente! ");
               break;
       }
   }

   public void iniciar(){
       do {
           menu();
           opcaoSelecionada();
           }while (opcao!=0);
   }
}
