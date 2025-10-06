package gradle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    int id;
    String codinome;
    int blindagem;
    int velocidade;
    int poderDeFogo;
    Date horaEntradaArena;
    int opTanque, opAliado;

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Tanque> tanquesAliado = new ArrayList<>();
        ArrayList<Tanque> tanqueInimigo = new ArrayList<>();
        Modos modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);


        int opcao;
        while (true) {
            try {
                System.out.println("#===# Menu #===#");
                System.out.printf("\n Tanques Aliados: %d", tanquesAliado.size());
                System.out.printf("\n Tanques Inimigos: %d", tanqueInimigo.size());
                System.out.println("\n(1) Criar tanque");
                System.out.println("(2) Começar partida");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();

            switch (opcao) {
                case 1:

                    sc.nextLine();

                    System.out.println("Você quer criar um tanque aliado ou inimigo?");
                    System.out.println("(1) Aliado");
                    System.out.println("(2) Inimigo");
                    int opAliado = sc.nextInt();

                    if (opAliado == 1) {
                        int id = random.nextInt(999) + 100;

                        System.out.print("Digite o codinome: ");
                        sc.nextLine(); // Limpa o buffer
                        String codinome = sc.nextLine();

                        System.out.print("Digite a blindagem: (Numero)");
                        int blindagem = sc.nextInt();

                        System.out.print("Digite a velocidade: ");
                        int velocidade = sc.nextInt();

                        System.out.print("Digite o poder de fogo: ");
                        int poderDeFogo = sc.nextInt();

                        System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                        int opTanque = sc.nextInt();
                        
                        Date horaEntrada = new Date();

                        if (opTanque == 1) {
                            tanquesAliado.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                            });
                            System.out.println("Tanque Leve adicionado com sucesso!");
                        } else if (opTanque == 2) {
                            tanquesAliado.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                            });
                            System.out.println("Tanque Médio adicionado com sucesso!");
                        } else if (opTanque == 3) {
                            tanquesAliado.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                            });
                            System.out.println("Tanque Pesado adicionado com sucesso!");
                        }

                    }

                    else if (opAliado == 2) {
                        int id = random.nextInt(999) + 100;

                        System.out.print("Digite o codinome: ");
                        sc.nextLine(); // Limpa o buffer
                        String codinome = sc.nextLine();

                        System.out.print("Digite a blindagem: (Numero)");
                        int blindagem = sc.nextInt();

                        System.out.print("Digite a velocidade: ");
                        int velocidade = sc.nextInt();

                        System.out.print("Digite o poder de fogo: ");
                        int poderDeFogo = sc.nextInt();

                        System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                        int opTanque = sc.nextInt();
                        
                        Date horaEntrada = new Date();

                        if (opTanque == 1) {
                            tanqueInimigo.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                            });
                            System.out.println("Tanque Leve inimigo adicionado com sucesso!");
                        } else if (opTanque == 2) {
                            tanqueInimigo.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                            });
                            System.out.println("Tanque Médio inimigo adicionado com sucesso!");
                        } else if (opTanque == 3) {
                            tanqueInimigo.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                            });
                            System.out.println("Tanque Pesado inimigo adicionado com sucesso!");
                        }
                    }

                    break;

                    case 2:
                    System.out.println("Selecione um modo");
                    System.out.println("(1) Treino");
                    System.out.println("(2) PvP");
                    System.out.println("(3) TvT");

                    int opModo = sc.nextInt();

                    if (opModo == 1){
                        modos.modoTreino();
                    }
                    

                    else if(opModo == 2){
                        modos.modoPvP();
                    }
                    

                    else if(opModo == 3){
                        modos.modoTvT();
                    }
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                sc.nextLine(); // Limpa o buffer
            } catch (java.util.NoSuchElementException e) {
                System.out.println("\nEncerrando o programa...");
                break;
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer
            }
        }
    }
}