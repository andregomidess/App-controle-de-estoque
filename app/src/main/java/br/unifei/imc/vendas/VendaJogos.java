//package br.unifei.imc.vendas;
//
//import br.unifei.imc.arquivo.LerArquivo;
////import br.unifei.imc.jogos.Games;
////import br.unifei.imc.jogos.JogoPc;
//import br.unifei.imc.jogos.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class VendaJogos {
//
//    public List<Games> carinho(){
//        List<Games> jogos = new ArrayList<>();
//
//        List<Games> jogosDesponiveis;
//        int op = 0;
//
//        Scanner myObj = new Scanner(System.in);
//        while (op != 1){
//            System.out.println("Escolha a Platafroma que deseja:" +
//                    "\n1-Jogos para Pc\n2-Jogos para Xbox\n3-Jogos para Playstation 5\n4-Jogos Multiplataforma\n");
//            int menu = myObj.nextInt();
//            myObj.nextLine();
//            if(menu==1){
//                jogosDesponiveis = LerArquivo.readProdutoCsv("jogosPc.csv");
//                JogoPc pc = new JogoPc();
//                String jogoDesejado;
//                pc.exibeJogo(jogosDesponiveis);
//                System.out.println("Digite o nome do jogo desejado:");
//                jogoDesejado = myObj.nextLine();
//                jogosDesponiveis.forEach(p->{
//                    if(jogoDesejado.equals(p.getNome())){
//                        jogos.add(new Games(p.getNome(), p.getValor(),p.getDescricao(),p.getFabricante()));
//                    }
//                });
//            }else if(menu==2){
//                jogosDesponiveis = LerArquivo.readProdutoCsv("jogosXbox.csv");
//                JogoXbox xbox = new JogoXbox();
//                String jogoDesejado;
//                xbox.exibeJogo(jogosDesponiveis);
//                System.out.println("Digite o nome do jogo desejado:");
//                jogoDesejado = myObj.nextLine();
//                jogosDesponiveis.forEach(p->{
//                    if(jogoDesejado.equals(p.getNome())){
//                        jogos.add(new Games(p.getNome(), p.getValor(),p.getDescricao(),p.getFabricante()));
//                    }
//                });
//            }else if(menu==3) {
//                jogosDesponiveis = LerArquivo.readProdutoCsv("jogosPlay5.csv");
//                JogoPlay5 play = new JogoPlay5();
//                String jogoDesejado;
//                play.exibeJogo(jogosDesponiveis);
//                System.out.println("Digite o nome do jogo desejado:");
//                jogoDesejado = myObj.nextLine();
//                jogosDesponiveis.forEach(p->{
//                    if(jogoDesejado.equals(p.getNome())){
//                        jogos.add(new Games(p.getNome(), p.getValor(),p.getDescricao(),p.getFabricante()));
//                    }
//                });
//            }else if(menu==4) {
//                jogosDesponiveis = LerArquivo.readProdutoCsv("jogosMultiplataforma.csv");
//                JogoMultiplataforma jm = new JogoMultiplataforma();
//                String jogoDesejado;
//                jm.exibeJogo(jogosDesponiveis);
//                System.out.println("Digite o nome do jogo desejado:");
//                jogoDesejado = myObj.nextLine();
//                jogosDesponiveis.forEach(p->{
//                    if(jogoDesejado.equals(p.getNome())){
//                        jogos.add(new Games(p.getNome(), p.getValor(),p.getDescricao(),p.getFabricante()));
//                    }
//                });
//            }else{
//                System.out.println("Opção não disponível!");
//            }
//            System.out.println("Escolher outro produto?(0-sim)(1-não)");
//            op = myObj.nextInt();
//        }
//
//        return jogos;
//    }
//
//}
